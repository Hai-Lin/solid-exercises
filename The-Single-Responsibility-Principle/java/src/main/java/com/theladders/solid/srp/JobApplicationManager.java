
package com.theladders.solid.srp;


import java.util.HashMap;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;


public class JobApplicationManager
{

  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;


  public JobApplicationManager(JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationSystem jobApplicationSystem,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }


  private boolean isResumeCompleteByPremiumUser(Jobseeker jobseeker,
                                                JobseekerProfile profile)
  {

    return !jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
                                      profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
                                      profile.getStatus().equals(ProfileStatus.REMOVED));
  }


  public JobApplicationResultStatus processJobApplication(JobApplicationInfo jobApplicationInfo)

  {
    Job job = jobApplicationInfo.getJob();
    Jobseeker jobseeker = jobApplicationInfo.getJobSeeker();
    HashMap<String, String> resumeInfo = jobApplicationInfo.getResumeInfo();
    if (job == null)
    {
      return JobApplicationResultStatus.JOB_NOT_FOUND;
    }
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);

    try
    {
      Resume resume = saveNewOrRetrieveExistingResume(resumeInfo, jobseeker);
      apply(jobseeker, job, resume);
    }
    catch (Exception e)
    {
      return JobApplicationResultStatus.INVALID;
    }
    if (isResumeCompleteByPremiumUser(jobseeker, profile))
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


  private void apply(Jobseeker jobseeker,
                     Job job,
                     Resume resume)
  {
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    com.theladders.solid.srp.job.application.JobApplicationResult applicationResult = jobApplicationSystem.apply(
            application);
    handleApplicationResult(applicationResult);
  }


  private Resume saveNewOrRetrieveExistingResume(HashMap<String, String> resumeInfo,
                                                 Jobseeker jobseeker)
  {
    Resume resume;
    if (!"existing".equals(resumeInfo.get("whichResumeString")))
    {
      resume = resumeManager.saveResume(jobseeker, resumeInfo.get("resumeName"));
      if (resume != null && "yes".equals(resumeInfo.get("makeResumeActiveString")))
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
