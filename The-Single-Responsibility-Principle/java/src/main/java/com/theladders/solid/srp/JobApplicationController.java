package com.theladders.solid.srp;


import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.JobApplicationResultView;
import com.theladders.solid.srp.view.JobApplicationResultViewGenerator;

public class JobApplicationController
{
  private final JobApplicationResultViewGenerator jobApplicationResultViewGenerator;
  private final JobApplicationRequestProcessor    jobApplicationRequestProcessor;
  private final JobApplicationManager             jobApplicationManager;


  public JobApplicationController(JobseekerProfileManager jobseekerProfileManager,
                                  JobSearchService jobSearchService,
                                  JobApplicationSystem jobApplicationSystem,
                                  ResumeManager resumeManager,
                                  MyResumeManager myResumeManager)
  {

    this.jobApplicationManager = new JobApplicationManager(jobseekerProfileManager,
                                                           jobApplicationSystem,
                                                           resumeManager,
                                                           myResumeManager);
    this.jobApplicationResultViewGenerator = new JobApplicationResultViewGenerator();
    this.jobApplicationRequestProcessor = new JobApplicationRequestProcessor(jobSearchService);
  }


  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    JobApplicationInfo jobApplicationInfo = jobApplicationRequestProcessor.processJobApplicationRequest(request);
    JobApplicationResultStatus resultStatus = jobApplicationManager.getApplicationResult(jobApplicationInfo);
    JobApplicationResultView jobApplicationResultView = jobApplicationResultViewGenerator.generateJobApplicationResultView(jobApplicationInfo,resultStatus);
    response.setJobApplicationResultView(jobApplicationResultView);
    return response;
  }


}
