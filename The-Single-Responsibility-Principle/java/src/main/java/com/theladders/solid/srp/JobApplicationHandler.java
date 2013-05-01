package com.theladders.solid.srp;

import java.util.HashMap;

import com.theladders.solid.srp.resume.ResumeInfo;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.result.FailedApplicationResult;
import com.theladders.solid.srp.result.InvalidJob;
import com.theladders.solid.srp.result.InvalidResume;
import com.theladders.solid.srp.result.Result;
import com.theladders.solid.srp.result.SuccessButProfileIncomplete;
import com.theladders.solid.srp.result.SuccessfulApplicationResult;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.job.application.JobApplicationResult;


public class JobApplicationHandler
{

  private final JobSeekerProfileManager jobSeekerProfileManager;
  private final JobApplicationSystem    jobApplicationSystem;
  private final HttpRequest             request;
  private final JobSearchService        jobSearchService;
  private final ResumeController        resumeController;


  public JobApplicationHandler(JobSeekerProfileManager jobSeekerProfileManager,
                               JobApplicationSystem jobApplicationSystem,
                               JobSearchService jobSearchService,
                               ResumeController resumeController,
                               HttpRequest request)
  {
    this.jobSeekerProfileManager = jobSeekerProfileManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.jobSearchService = jobSearchService;
    this.resumeController = resumeController;
    this.request = request;
  }


  private Resume processResume()
  {
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    HashMap<String, String> resumeInfoHash = new HashMap<>();
    resumeInfoHash.put("resumeName", resumeName);
    resumeInfoHash.put("whichResumeString", whichResumeString);
    resumeInfoHash.put("makeResumeActiveString", makeResumeActiveString);
    ResumeInfo resumeInfo = new ResumeInfo(resumeInfoHash);
    return resumeController.processResume(resumeInfo,
                                          request.getSession().getJobSeeker());
  }


  public Result processJobApplication()
  {
    Job job = jobSearchService.getJob(Integer.parseInt(request.getParameter("jobId")));
    if (job == null)
    {
      return new InvalidJob(request.getParameter("jobId"));
    }
    Resume resume = processResume();
    if (resume == null)
      return new InvalidResume();

    UnprocessedApplication application = new UnprocessedApplication(request.getSession().getJobSeeker(), job, resume);
    return processValidJobApplication(application);
  }





  private Result processValidJobApplication(UnprocessedApplication unprocessedApplication)
  {
    JobApplicationResult applicationResult = jobApplicationSystem.apply(
            unprocessedApplication);

    if (applicationResult.failure())
    {
      return new FailedApplicationResult();
    }

    if (jobSeekerProfileManager.isResumeIncomplete(unprocessedApplication.getJobSeeker()))
    {
      return new SuccessButProfileIncomplete();
    }
    return new SuccessfulApplicationResult();

  }

}
