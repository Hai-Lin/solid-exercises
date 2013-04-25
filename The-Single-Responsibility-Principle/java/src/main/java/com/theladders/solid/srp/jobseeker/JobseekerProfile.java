package com.theladders.solid.srp.jobseeker;

public class JobSeekerProfile
{
  private final int id;
  private final ProfileStatus status;

  public JobSeekerProfile(int id,
                          ProfileStatus status)
  {
    this.id = id;
    this.status = status;
  }

  public ProfileStatus getStatus()
  {
    return status;
  }

  public int getId()
  {
    return id;
  }
}
