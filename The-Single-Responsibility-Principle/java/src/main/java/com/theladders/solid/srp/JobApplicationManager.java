package com.theladders.solid.srp;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.result.FailedApplicationResult;
import com.theladders.solid.srp.result.InvalidJob;
import com.theladders.solid.srp.result.InvalidResume;
import com.theladders.solid.srp.result.Result;
import com.theladders.solid.srp.result.SuccessButProfileIncomplete;
import com.theladders.solid.srp.result.SuccessfulApplicationResult;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.job.application.JobApplicationResult;


public class JobApplicationManager
{

  private final JobSeekerProfileManager jobSeekerProfileManager;
  private final JobApplicationSystem    jobApplicationSystem;


  public JobApplicationManager(JobSeekerProfileManager jobSeekerProfileManager,
                               JobApplicationSystem jobApplicationSystem)
  {
    this.jobSeekerProfileManager = jobSeekerProfileManager;
    this.jobApplicationSystem = jobApplicationSystem;
  }


  public Result processJobApplication(JobApplicationInfo jobApplicationInfo)
  {
    Job job = jobApplicationInfo.getJob();
    JobSeeker jobSeeker = jobApplicationInfo.getJobSeeker();
    Resume resume = jobApplicationInfo.getResume();
    if (resume == null)
    {
      return new InvalidResume(jobApplicationInfo);
    }
    if (job == null)
    {
      return new InvalidJob(jobApplicationInfo);
    }
    try
    {
      apply(jobSeeker, job, resume);
    }
    catch (Exception e)
    {
      return new FailedApplicationResult(jobApplicationInfo);
    }
    if (jobSeekerProfileManager.isResumeIncomplete(jobSeeker))
    {
      return new SuccessButProfileIncomplete(jobApplicationInfo);
    }
    return new SuccessfulApplicationResult(jobApplicationInfo);
  }


  private void handleApplicationResult(JobApplicationResult result)
  {
    if (result.failure())
    {
      throw new ApplicationFailureException(result.toString());
    }
  }


  private void apply(JobSeeker jobSeeker,
                     Job job,
                     Resume resume)
  {
    UnprocessedApplication application = new UnprocessedApplication(jobSeeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(
            application);
    handleApplicationResult(applicationResult);
  }
}
