package com.theladders.solid.srp.job.application;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.resume.Resume;

public class SuccessfulApplication implements JobApplicationResult
{
  private final JobSeeker jobSeeker;
  private final Job       job;
  private final Resume    resume;


  public SuccessfulApplication(JobSeeker jobSeeker,
                               Job job,
                               Resume resume)
  {
    this.jobSeeker = jobSeeker;
    this.job = job;
    this.resume = resume;
  }


  @Override
  public boolean failure()
  {
    return false;
  }


  public Object getJobSeeker()
  {
    return jobSeeker;
  }

  public Object getJob()
  {
    return job;
  }
}
