package com.theladders.solid.srp.job.application;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.resume.Resume;

public class UnprocessedApplication
{
  private final JobSeeker jobSeeker;
  private final Job       job;
  private final Resume    resume;


  public UnprocessedApplication(JobSeeker jobSeeker,
                                Job job,
                                Resume resume)
  {
    this.jobSeeker = jobSeeker;
    this.job = job;
    this.resume = resume;
  }


  public boolean isValid()
  {
    return jobSeeker != null &&
           job       != null &&
           resume    != null;
  }

  public JobSeeker getJobSeeker()
  {
    return jobSeeker;
  }

  public Job getJob()
  {
    return job;
  }

  public Resume getResume()
  {
    return resume;
  }
}
