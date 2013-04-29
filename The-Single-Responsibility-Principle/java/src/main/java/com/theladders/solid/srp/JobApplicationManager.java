package com.theladders.solid.srp;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.jobseeker.JobSeekerProfile;
import com.theladders.solid.srp.jobseeker.JobSeekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.resume.Resume;


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


  private boolean isResumeCompleteByPremiumUser(JobSeeker jobSeeker)
  {
    JobSeekerProfile profile = jobSeekerProfileManager.getJobSeekerProfile(jobSeeker);


    return !jobSeeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
                                      profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
                                      profile.getStatus().equals(ProfileStatus.REMOVED));
  }


  public JobApplicationResultStatus processJobApplication(JobApplicationInfo jobApplicationInfo)

  {
    Job job = jobApplicationInfo.getJob();
    JobSeeker jobSeeker = jobApplicationInfo.getJobSeeker();
    Resume resume = jobApplicationInfo.getResume();


    if (resume == null)
    {
      return JobApplicationResultStatus.INVALID;
    }

    if (job == null)
    {
      return JobApplicationResultStatus.JOB_NOT_FOUND;
    }
    try
    {
      apply(jobSeeker, job, resume);
    }
    catch (Exception e)
    {
      return JobApplicationResultStatus.INVALID;
    }
    if (isResumeCompleteByPremiumUser(jobSeeker))
    {
      return JobApplicationResultStatus.RESUME_NOT_COMPLETE;
    }
    return JobApplicationResultStatus.SUCCESS;
  }


  private void handleApplicationResult(com.theladders.solid.srp.job.application.JobApplicationResult result)
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
    com.theladders.solid.srp.job.application.JobApplicationResult applicationResult = jobApplicationSystem.apply(
            application);
    handleApplicationResult(applicationResult);
  }


}
