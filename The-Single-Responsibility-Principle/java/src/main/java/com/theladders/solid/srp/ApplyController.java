package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.ApplicationManager;
import com.theladders.solid.srp.ApplicationResultSatate;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;


  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }


  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    Jobseeker jobseeker = request.getSession().getJobseeker();
    String jobIdString = request.getParameter("jobId");
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);


    try
    {
      Resume resume = saveNewOrRetrieveExistingResume(resumeName, jobseeker, whichResumeString, makeResumeActiveString);
    }

    catch (Exception e)
    {
      System.out.println("resume can't be null");
    }
    Result result = getApplicationResult(jobseeker,
                                         resumeName,
                                         jobIdString,
                                         profile,
                                         whichResumeString,
                                         makeResumeActiveString);

    response.setResult(result);
    return response;


  }


  private boolean isResumeCompleteByPremiumUser(Jobseeker jobseeker,
                                                JobseekerProfile profile)
  {

    return !jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
                                      profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
                                      profile.getStatus().equals(ProfileStatus.REMOVED));
  }


  private Result getApplicationResult(Jobseeker jobseeker,
                                      String resumeName,
                                      String jobIdString,
                                      JobseekerProfile profile,
                                      String whichResumeString,
                                      String makeResumeActiveString)

  {
    Job job = jobSearchService.getJob(Integer.parseInt(jobIdString));
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    if (job == null)
    {
      model.put("jobId", Integer.parseInt(jobIdString));
      return new Result("invalidJob", model);

    }
    try
    {
      Resume resume = saveNewOrRetrieveExistingResume(resumeName, jobseeker, whichResumeString, makeResumeActiveString);
      ApplicationManager applicationManager = new ApplicationManager(job, resume, jobseeker);
      apply(jobseeker, job, resume);
    }
    catch (Exception e)
    {
      errList.add("We could not process your application.");
      return new Result("error", model, errList);
    }
    if (isResumeCompleteByPremiumUser(jobseeker, profile))
    {
      return new Result("completeResumePlease", model);
    }
    return new Result("success", model);
  }


  private void apply(
          Jobseeker jobseeker,
          Job job,
          Resume resume)
  {
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }


  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 String whichResumeString,
                                                 String makeResumeActiveString)
  {
    Resume resume;

    if (!"existing".equals(whichResumeString))
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);
      if (resume != null && "yes".equals(makeResumeActiveString))
      {
        myResumeManager.saveAsActive(jobseeker, resume);
      }
    }
    else
    {
      resume = myResumeManager.getActiveResume(jobseeker.getId());
    }

    return resume;
  }


}
