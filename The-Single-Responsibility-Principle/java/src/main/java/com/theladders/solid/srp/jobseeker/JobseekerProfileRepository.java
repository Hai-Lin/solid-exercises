package com.theladders.solid.srp.jobseeker;

import java.util.HashMap;
import java.util.Map;

public class JobSeekerProfileRepository
{
  private Map<Integer, JobSeekerProfile> profiles;


  public JobSeekerProfileRepository()
  {
    this.profiles = new HashMap<>();
  }


  public JobSeekerProfile getProfile(JobSeekerId jobseekerId)
  {
    return profiles.get(jobseekerId.getInt());
  }

  public void addProfile(JobSeekerProfile profile)
  {
    profiles.put(profile.getId(), profile);
  }
}
