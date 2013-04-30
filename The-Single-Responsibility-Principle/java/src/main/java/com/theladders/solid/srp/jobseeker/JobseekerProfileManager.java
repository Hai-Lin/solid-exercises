package com.theladders.solid.srp.jobseeker;


public class JobSeekerProfileManager
{
  private final JobSeekerProfileRepository repository;


  public JobSeekerProfileManager(JobSeekerProfileRepository repository)
  {
    this.repository = repository;
  }

  private JobSeekerProfile getJobSeekerProfile(JobSeeker jobSeeker)
  {
    return  repository.getProfile(jobSeeker.getId());
  }

  public boolean isResumeIncomplete(JobSeeker jobSeeker)
  {
    JobSeekerProfile profile = this.getJobSeekerProfile(jobSeeker);
    return !jobSeeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
                                      profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
                                      profile.getStatus().equals(ProfileStatus.REMOVED));
  }
}
