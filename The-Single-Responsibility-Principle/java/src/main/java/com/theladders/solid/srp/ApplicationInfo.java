package com.theladders.solid.srp;

import java.util.HashMap;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ApplicationInfo
{
  private final String                  jobIdString;
  private final Jobseeker               jobSeeker;
  private final HashMap<String, String> resumeInfo;

  public ApplicationInfo(String jobIdString,
                         Jobseeker jobSeeker,
                         String makeResumeActiveString,
                         String resumeName,
                         String whichResumeString)
  {
    this.jobIdString = jobIdString;
    this.jobSeeker = jobSeeker;
    this.resumeInfo = new HashMap<>();
    resumeInfo.put(resumeName, resumeName);
    resumeInfo.put(whichResumeString, whichResumeString);
    resumeInfo.put(makeResumeActiveString, makeResumeActiveString);
  }
  public String getJobId()
  {
    return this.jobIdString;
  }

  public Jobseeker getJobSeeker()
  {
    return this.jobSeeker;
  }

  public HashMap getResumeInfo()
  {
    return this.resumeInfo;
  }
}
