package com.theladders.solid.srp;



import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.ApplicationResultSatate;

public class ApplyController
{
  private final JobSearchService jobSearchService;

  private final ApplicationManager applicationManager;


  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {

    this.jobSearchService = jobSearchService;
    this.applicationManager = new ApplicationManager(jobseekerProfileManager,
                                                     jobApplicationSystem,
                                                     resumeManager,
                                                     myResumeManager);
  }

  private HttpResponse handleNullJob(HttpResponse response, String jobIdString)
  {
    Result result = applicationManager.handleNullJob(jobIdString);
    response.setResult(result);
    return response;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    String jobIdString = request.getParameter("jobId");
    Jobseeker jobseeker = request.getSession().getJobseeker();
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    Job job = jobSearchService.getJob(Integer.parseInt(jobIdString));
    if (job == null)
    {
      return handleNullJob(response, jobIdString);
    }
    Result result = applicationManager.getApplicationResult(jobseeker,
                                         resumeName,
                                         job,
                                         whichResumeString,
                                         makeResumeActiveString);

    response.setResult(result);
    return response;
  }


}
