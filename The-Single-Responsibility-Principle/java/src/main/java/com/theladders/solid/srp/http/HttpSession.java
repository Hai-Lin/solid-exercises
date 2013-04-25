package com.theladders.solid.srp.http;

import com.theladders.solid.srp.jobseeker.JobSeeker;

public class HttpSession
{
  private final JobSeeker jobSeeker;


  public HttpSession(JobSeeker jobSeeker)
  {
    this.jobSeeker = jobSeeker;
  }


  public JobSeeker getJobSeeker()
  {
    return jobSeeker;
  }
}
