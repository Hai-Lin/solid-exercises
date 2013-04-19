package com.theladders.solid.srp.ResumeController;


import java.util.HashMap;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ResumeController
{

  private ResumeManager resumeManager;
  private MyResumeManager myResumeManager;

  public ResumeController(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public ResumeProcessResult processResume(HashMap<String, String> resumeInfo, Jobseeker jobseeker)
  {
    if(isResumeInvalid(resumeInfo))
      return new ResumeProcessResult(null, ResumeProcessResultStatus.INVALID);
    Resume resume = saveNewOrRetrieveExistingResume(resumeInfo, jobseeker);
    return new ResumeProcessResult(resume, ResumeProcessResultStatus.SUCCESS);
  }



  private boolean isResumeInvalid(HashMap<String ,String> resumeInfo)
  {
    return !"existing".equals(resumeInfo.get("whichResumeString")) && resumeInfo.get("resumeName") == null;

  }
  private Resume saveNewOrRetrieveExistingResume(HashMap<String, String> resumeInfo,
                                                 Jobseeker jobseeker)
  {
    Resume resume;
    if (!"existing".equals(resumeInfo.get("whichResumeString")) )
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
