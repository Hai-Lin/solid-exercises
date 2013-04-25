package com.theladders.solid.srp.jobseeker;


public class JobSeekerProfileManager
{
  private final JobSeekerProfileRepository repository;


  public JobSeekerProfileManager(JobSeekerProfileRepository repository)
  {
    this.repository = repository;
  }

  public JobSeekerProfile getJobSeekerProfile(JobSeeker jobSeeker)
  {
    return  repository.getProfile(jobSeeker.getId());
  }
}
