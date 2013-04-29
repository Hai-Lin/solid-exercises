package com.theladders.solid.srp.applicationInfo;

import java.util.HashMap;

import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.resume.Resume;

public class JobApplicationInfo
{
  private final String    jobIdString;
  private final JobSeeker jobSeeker;
  private final Job       job;
  private final Resume    resume;


  public JobApplicationInfo(String jobIdString,
                            JobSeeker jobSeeker,
                            Resume resume,
                            Job job)
  {
    this.jobIdString = jobIdString;
    this.job = job;
    this.jobSeeker = jobSeeker;
    this.resume = resume;
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


  public Resume getResume()
  {
    return this.resume;
  }
}
