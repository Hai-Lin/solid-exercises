package com.theladders.solid.srp.applicationInfo;

import java.util.HashMap;

import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.job.Job;

public class JobApplicationInfo
{
  private final String     jobIdString;
  private final JobSeeker  jobSeeker;
  private final Job        job;
  private final ResumeInfo resumeInfo;


  public JobApplicationInfo(String jobIdString,
                            JobSeeker jobSeeker,
                            HashMap<String, String> resumeInfo,
                            Job job)
  {
    this.jobIdString = jobIdString;
    this.job = job;
    this.resumeInfo = new ResumeInfo(resumeInfo);
    this.jobSeeker = jobSeeker;
  }


  public String getJobId()
  {
    return this.jobIdString;
  }


  public Job getJob()
  {
    return job;
  }


  public JobSeeker getJobSeeker()
  {
    return this.jobSeeker;
  }


  public ResumeInfo getResumeInfo()
  {
    return this.resumeInfo;
  }
}
