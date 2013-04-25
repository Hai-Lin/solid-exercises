package com.theladders.solid.srp;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobRepository;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationRepository;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.SuccessfulApplication;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.jobseeker.JobSeekerId;
import com.theladders.solid.srp.jobseeker.JobSeekerProfile;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileRepository;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.resume.ActiveResumeRepository;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeRepository;

public class TestIt
{
  private static final int    INVALID_JOB_ID        = 555;
  private static final String SHARED_RESUME_NAME    = "A Resume";
  private static final int    JOBSEEKER_WITH_RESUME = 777;
  private static final int    INCOMPLETE_JOBSEEKER  = 888;
  private static final int    APPROVED_JOBSEEKER    = 1010;

  private JobApplicationController   controller;
  private JobRepository              jobRepository;
  private ResumeRepository           resumeRepository;
  private JobApplicationRepository   jobApplicationRepository;
  private JobSeekerProfileRepository jobSeekerProfileRepository;
  private ActiveResumeRepository     activeResumeRepository;

  private SuccessfulApplication existingApplication;


  @Test
  public void requestWithValidJob()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", "5");
    parameters.put("resumeName", SHARED_RESUME_NAME);

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("success", response.getResultType());
  }


  @Test
  public void requestWithValidJobByBasic()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, false);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", "5");
    parameters.put("resumeName", SHARED_RESUME_NAME);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("success", response.getResultType());
  }


  @Test
  public void applyUsingExistingResume()
  {
    JobSeeker JobSeeker = new JobSeeker(JOBSEEKER_WITH_RESUME, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("whichResume", "existing");
    parameters.put("resumeName", SHARED_RESUME_NAME);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void requestWithInvalidJob()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", String.valueOf(INVALID_JOB_ID));
    parameters.put("resumeName", SHARED_RESUME_NAME);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("invalidJob", response.getResultType());
  }

  @Test
  public void requestWithNoResume()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", "5");
    parameters.put("resumeName", null);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void reapplyToJob()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","15");
    parameters.put("resumeName", SHARED_RESUME_NAME);

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void unapprovedBasic()
  {
    JobSeeker JobSeeker = new JobSeeker(INCOMPLETE_JOBSEEKER, false);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("resumeName", SHARED_RESUME_NAME);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals("completeResumePlease", response.getResultType());
  }

  @Test
  public void resumeIsSaved()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(JobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("resumeName", SHARED_RESUME_NAME);


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
  }

  @Test
  public void resumeIsMadeActive()
  {
    JobSeeker jobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    HttpSession session = new HttpSession(jobSeeker);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("makeResumeActive", "yes");
    parameters.put("resumeName", "Save Me Seymour");


    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response);

    assertEquals(new Resume("Save Me Seymour"), activeResumeRepository.activeResumeFor(jobSeeker.getId()));
  }

  @Before
  public void setup()
  {
    setupJobseekerProfileRepository();
    setupJobRepository();
    setupResumeRepository();
    setupActiveResumeRepository();
    setupJobApplicationRepository();
    setupController();
  }

  private void setupJobseekerProfileRepository()
  {
    jobSeekerProfileRepository = new JobSeekerProfileRepository();

    addToJobseekerProfileRepository(APPROVED_JOBSEEKER, ProfileStatus.APPROVED);
    addToJobseekerProfileRepository(INCOMPLETE_JOBSEEKER, ProfileStatus.INCOMPLETE);
    addToJobseekerProfileRepository(JOBSEEKER_WITH_RESUME, ProfileStatus.APPROVED);
  }

  private void addToJobseekerProfileRepository(int id, ProfileStatus status)
  {
    JobSeekerProfile profile = new JobSeekerProfile(id, status);
    jobSeekerProfileRepository.addProfile(profile);
  }

  private void setupJobRepository()
  {
    jobRepository = new JobRepository();

    addJobToRepository(5);
    addJobToRepository(15);
    addJobToRepository(51);
    addJobToRepository(57);
    addJobToRepository(501);
    addJobToRepository(1555);
    addJobToRepository(5012);
    addJobToRepository(50111);
  }

  private void addJobToRepository(int jobId)
  {
    if (jobId != INVALID_JOB_ID)
    {
      jobRepository.addJob(new Job(jobId));
    }
  }

  private void setupResumeRepository()
  {
    resumeRepository = new ResumeRepository();
  }

  private void setupActiveResumeRepository()
  {
    activeResumeRepository = new ActiveResumeRepository();

    JobSeekerId jobSeekerId = new JobSeekerId(JOBSEEKER_WITH_RESUME);

    activeResumeRepository.makeActive(jobSeekerId, new Resume("Blammo"));
  }

  private void setupJobApplicationRepository()
  {
    jobApplicationRepository = new JobApplicationRepository();

    addToJobApplicationRepository();
  }

  private void addToJobApplicationRepository()
  {
    JobSeeker JobSeeker = new JobSeeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(15);
    Resume resume = new Resume("foo");

    existingApplication = new SuccessfulApplication(JobSeeker, job, resume);

    jobApplicationRepository.add(existingApplication);
  }

  private void setupController()
  {
    JobSeekerProfileManager jobSeekerProfileManager = new JobSeekerProfileManager(jobSeekerProfileRepository);
    JobSearchService jobSearchService = new JobSearchService(jobRepository);
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(resumeRepository, activeResumeRepository);

    controller = new JobApplicationController(jobSeekerProfileManager,
                                     jobSearchService,
                                     jobApplicationSystem,
                                     resumeManager);
  }
}
