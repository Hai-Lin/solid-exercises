package com.theladders.solid.srp;


import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{
  private final ResumeManager    resumeManager;
  private final MyResumeManager  myResumeManager;
  private final ResultProvider   resultProvider;
  private final JobSearchService jobSearchService;


  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {

    this.jobSearchService = jobSearchService;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
    this.resultProvider = new ResultProvider(jobSearchService,
                                             resumeManager,
                                             myResumeManager,
                                             jobApplicationSystem,
                                             jobseekerProfileManager);
  }


  private ApplicationInfo processRequest(HttpRequest request, JobSearchService jobSearchService)
  {
    String jobIdString = request.getParameter("jobId");
    Jobseeker jobseeker = request.getSession().getJobseeker();
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    return new ApplicationInfo(jobIdString, jobseeker, makeResumeActiveString, resumeName, whichResumeString, jobSearchService);
  }


  public HttpResponse handle(HttpRequest request,
                             HttpResponse response)
  {
    ApplicationInfo applicationInfo = processRequest(request, this.jobSearchService);
    Result result = resultProvider.provideApplicationResult(applicationInfo);
    response.setResult(result);
    return response;
  }


}
