package com.theladders.solid.srp;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.applicationInfo.JobApplicationInfoGenerator;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.result.Result;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.JobApplicationResultView;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public class JobApplicationController
{
  private final JobApplicationResultViewFactory jobApplicationResultViewFactory;
  private final JobApplicationInfoGenerator     jobApplicationInfoGenerator;
  private final JobApplicationManager           jobApplicationManager;


  public JobApplicationController(JobSeekerProfileManager jobSeekerProfileManager,
                                  JobSearchService jobSearchService,
                                  JobApplicationSystem jobApplicationSystem,
                                  ResumeManager resumeManager)
  {

    this.jobApplicationManager = new JobApplicationManager(jobSeekerProfileManager,
                                                           jobApplicationSystem);
    this.jobApplicationResultViewFactory = new JobApplicationResultViewFactory();
    this.jobApplicationInfoGenerator = new JobApplicationInfoGenerator(jobSearchService, resumeManager);
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    JobApplicationInfo jobApplicationInfo = jobApplicationInfoGenerator.preprocessRequest(request);
    Result result = jobApplicationManager.processJobApplication(jobApplicationInfo);
    result.render(response, jobApplicationResultViewFactory);
    return response;
  }
}
