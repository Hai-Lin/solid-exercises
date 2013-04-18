package com.theladders.solid.srp;

import java.util.HashMap;

import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.job.Job;

public class JobApplicationInfo
{
  private final String                  jobIdString;
  private final Jobseeker               jobSeeker;
  private final HashMap<String, String> resumeInfo;
  private final Job                     job;


  public JobApplicationInfo(String jobIdString, Jobseeker jobseeker, HashMap<String, String> resumeInfo, Job job)
  {
    this.jobIdString = jobIdString;
    this.job = job;
    this.resumeInfo = resumeInfo;
    this.jobSeeker = jobseeker;
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


  public HashMap<String, String> getResumeInfo()
  {
    return this.resumeInfo;
  }
}
