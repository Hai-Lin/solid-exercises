package com.theladders.solid.srp;


import com.theladders.solid.srp.applicationInfo.ResumeInfo;
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
    return saveNewOrRetrieveExistingResume(resumeInfo, jobSeeker);
  }


  private Resume saveNewOrRetrieveExistingResume(ResumeInfo resumeInfo,
                                                 JobSeeker jobSeeker)
  {
    Resume resume;
    if (!resumeInfo.isExisting())
    {
      resume = saveResume(jobSeeker, resumeInfo);
    }
    else
    {
      resume = resumeManager.getActiveResume(jobSeeker.getId());
    }

    return resume;
  }


  private Resume saveResume(JobSeeker jobSeeker,
                            ResumeInfo resumeInfo)
  {
    Resume resume = new Resume(resumeInfo);
    resumeManager.saveResume(jobSeeker.getId(), resume);
    if (resumeInfo.isMakedActive())
    {
      resumeManager.saveAsActive(jobSeeker.getId(), resume);
    }
    return resume;
  }
}
