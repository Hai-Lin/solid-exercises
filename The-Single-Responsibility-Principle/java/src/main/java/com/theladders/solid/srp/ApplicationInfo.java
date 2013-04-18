package com.theladders.solid.srp;

import java.util.HashMap;

import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.job.Job;

public class ApplicationInfo
{
  private final String                  jobIdString;
  private final Jobseeker               jobSeeker;
  private final HashMap<String, String> resumeInfo;
  private final Job                     job;


  public ApplicationInfo(String jobIdString,
                         Jobseeker jobSeeker,
                         String makeResumeActiveString,
                         String resumeName,
                         String whichResumeString,
                         JobSearchService jobSearchService)
  {
    this.jobIdString = jobIdString;
    this.jobSeeker = jobSeeker;
    this.resumeInfo = new HashMap<>();
    resumeInfo.put(resumeName, resumeName);
    resumeInfo.put(whichResumeString, whichResumeString);
    resumeInfo.put(makeResumeActiveString, makeResumeActiveString);
    this.job = jobSearchService.getJob(Integer.parseInt(jobIdString));
  }


  public String getJobId()
  {
    return this.jobIdString;
  }

  public Job getJob()
  {
    return job;
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
