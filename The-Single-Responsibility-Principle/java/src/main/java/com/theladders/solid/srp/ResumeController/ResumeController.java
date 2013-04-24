package com.theladders.solid.srp.ResumeController;


import java.util.HashMap;

import com.theladders.solid.srp.ResumeInfo;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.MyResumeManager;


public class ResumeController
{

  private ResumeManager resumeManager;
  private MyResumeManager myResumeManager;

  public ResumeController(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public ResumeProcessResult processResume(ResumeInfo resumeInfo, Jobseeker jobseeker)
  {
    if(resumeInfo.getName() == null && !resumeInfo.isExisting())
      return new ResumeProcessResult(null, ResumeProcessResultStatus.INVALID);
    Resume resume = saveNewOrRetrieveExistingResume(resumeInfo, jobseeker);
    return new ResumeProcessResult(resume, ResumeProcessResultStatus.SUCCESS);
  }



  private Resume saveNewOrRetrieveExistingResume(ResumeInfo resumeInfo,
                                                 Jobseeker jobseeker)
  {
    Resume resume;
    if (!resumeInfo.isExisting())
    {
      resume = resumeManager.saveResume(jobseeker, resumeInfo.getName());
      if (resume != null && resumeInfo.isMakedActive())
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
