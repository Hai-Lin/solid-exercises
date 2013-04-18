package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplicatoinInfo
{
  private final String    jobIdString;
  private final Jobseeker jobseeker;
  private final String    makeResumeActiveString;
  private final String    resumeName;
  private final String    whichResumeString;
  private final ResumeManager resumeManager;
  private final MyResumeManager myResumeManager;


  public ApplicatoinInfo(String jobIdString,
                         Jobseeker jobseeker,
                         String makeResumeActiveString,
                         String resumeName,
                         String whichResumeString,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager
  )
  {
    this.jobIdString = jobIdString;
    this.jobseeker = jobseeker;
    this.makeResumeActiveString = makeResumeActiveString;
    this.resumeName = resumeName;
    this.whichResumeString = whichResumeString;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public Resume saveNewOrRetrieveExistingResume()
  {
    Resume resume;

    if (!"existing".equals(this.whichResumeString))
    {
      resume = resumeManager.saveResume(jobseeker, this.resumeName);
      if (resume != null && "yes".equals(makeResumeActiveString))
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
