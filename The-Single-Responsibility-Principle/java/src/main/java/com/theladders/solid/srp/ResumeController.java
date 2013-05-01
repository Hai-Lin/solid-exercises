package com.theladders.solid.srp;


import com.theladders.solid.srp.resume.ResumeInfo;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;


public class ResumeController
{

  private ResumeManager resumeManager;


  public ResumeController(ResumeManager resumeManager)
  {
    this.resumeManager = resumeManager;
  }


  public Resume processResume(ResumeInfo resumeInfo,
                              JobSeeker jobSeeker)
  {
    if (resumeInfo.getName() == null && !resumeInfo.isExisting())
    {
      return null;
    }
    if (resumeInfo.isExisting())
    {
      return resumeManager.getActiveResume(jobSeeker);
    }

    Resume resume = new Resume(resumeInfo);

    resumeManager.saveResume(jobSeeker, resume);

    if (resumeInfo.isMakeActive())
    {
      resumeManager.saveAsActive(jobSeeker, resume);
    }
    return resume;
  }

}
