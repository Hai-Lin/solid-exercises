package com.theladders.solid.srp;


import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.result.Result;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public class JobApplicationController
{
  private final JobApplicationResultViewFactory jobApplicationResultViewFactory;
  private final ResumeController resumeController;
  private final JobSeekerProfileManager jobSeekerProfileManager;
  private final JobApplicationSystem   jobApplicationSystem;
  private final JobSearchService jobSearchService;



  public JobApplicationController(JobSeekerProfileManager jobSeekerProfileManager,
                                  JobSearchService jobSearchService,
                                  JobApplicationSystem jobApplicationSystem,
                                  ResumeManager resumeManager)
  {
    this.resumeController = new ResumeController(resumeManager);
    this.jobApplicationSystem = jobApplicationSystem;
    this.jobSearchService = jobSearchService;
    this.jobSeekerProfileManager = jobSeekerProfileManager;
    this.jobApplicationResultViewFactory = new JobApplicationResultViewFactory();
  }


  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    JobApplicationHandler jobApplicationHandler =  new JobApplicationHandler(jobSeekerProfileManager,
                                                                             jobApplicationSystem,
                                                                             jobSearchService,
                                                                             resumeController,
                                                                             request);
    Result result = jobApplicationHandler.processJobApplication();
    result.render(response, jobApplicationResultViewFactory);
    return response;
  }
}
