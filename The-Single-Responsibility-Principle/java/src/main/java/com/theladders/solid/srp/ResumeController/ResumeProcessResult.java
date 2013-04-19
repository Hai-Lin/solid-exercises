package com.theladders.solid.srp.ResumeController;

import com.theladders.solid.srp.resume.Resume;

public class ResumeProcessResult
{
  private Resume                    resume;
  private ResumeProcessResultStatus resumeProcessResultStatus;


  public ResumeProcessResult(Resume resume,
                             ResumeProcessResultStatus resumeProcessResultStatus)
  {
    this.resume = resume;
    this.resumeProcessResultStatus = resumeProcessResultStatus;
  }


  public boolean isResumeValid()
  {
    return this.resumeProcessResultStatus == ResumeProcessResultStatus.SUCCESS;
  }


  public Resume getResume()
  {
    return this.resume;
  }
}
