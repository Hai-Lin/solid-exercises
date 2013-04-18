package com.theladders.solid.srp;


import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.JobApplicationResultView;
import com.theladders.solid.srp.view.JobApplicationResultViewProvider;

public class JobApplicationController
{
  private final ResumeManager                    resumeManager;
  private final MyResumeManager                  myResumeManager;
  private final JobApplicationResultViewProvider jobApplicationResultViewProvider;
  private final JobSearchService                 jobSearchService;
  private final JobApplicationRequestProcessor   jobApplicationRequestProcessor;


  public JobApplicationController(JobseekerProfileManager jobseekerProfileManager,
                                  JobSearchService jobSearchService,
                                  JobApplicationSystem jobApplicationSystem,
                                  ResumeManager resumeManager,
                                  MyResumeManager myResumeManager)
  {

    this.jobSearchService = jobSearchService;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
    this.jobApplicationResultViewProvider = new JobApplicationResultViewProvider(jobSearchService,
                                                                                 resumeManager,
                                             myResumeManager,
                                             jobApplicationSystem,
                                             jobseekerProfileManager);
    this.jobApplicationRequestProcessor = new JobApplicationRequestProcessor(jobSearchService);
  }


  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    JobApplicationInfo jobApplicationInfo = jobApplicationRequestProcessor.processJobApplicationRequest(request);
    JobApplicationResultView jobApplicationResultView = jobApplicationResultViewProvider.provideApplicationResult(jobApplicationInfo);
    response.setJobApplicationResultView(jobApplicationResultView);
    return response;
  }


}
