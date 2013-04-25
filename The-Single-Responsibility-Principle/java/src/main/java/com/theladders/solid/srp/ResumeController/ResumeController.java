package com.theladders.solid.srp.resumeController;


import com.theladders.solid.srp.applicationInfo.ResumeInfo;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;


public class ResumeController
{

  private ResumeManager   resumeManager;


  public ResumeController(ResumeManager resumeManager)
  {
    this.resumeManager = resumeManager;
  }


  public ResumeProcessResult processResume(ResumeInfo resumeInfo,
                                           JobSeeker jobSeeker)
  {
    if (resumeInfo.getName() == null && !resumeInfo.isExisting())
    {
      return new ResumeProcessResult(null, ResumeProcessResultStatus.INVALID);
    }
    Resume resume = saveNewOrRetrieveExistingResume(resumeInfo, jobSeeker);
    return new ResumeProcessResult(resume, ResumeProcessResultStatus.SUCCESS);
  }


  private Resume saveNewOrRetrieveExistingResume(ResumeInfo resumeInfo,
                                                 JobSeeker jobSeeker)
  {
    Resume resume;
    if (!resumeInfo.isExisting())
    {
      resume = new Resume(resumeInfo);
      resumeManager.saveResume(jobSeeker.getId(), resume);
      if (resumeInfo.isMakedActive())
      {
        resumeManager.saveAsActive(jobSeeker.getId(), resume);
      }
    }
    else
    {
      resume = resumeManager.getActiveResume(jobSeeker.getId());
    }

    return resume;
  }
}
