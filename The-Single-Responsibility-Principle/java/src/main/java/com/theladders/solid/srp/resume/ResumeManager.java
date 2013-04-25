package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.jobseeker.JobSeekerId;

public class ResumeManager
{
  private final ResumeRepository resumeRepository;
  private final ActiveResumeRepository activeResumeRepository;


  public ResumeManager(ResumeRepository resumeRepository, ActiveResumeRepository activeResumeRepository)
  {
    this.activeResumeRepository = activeResumeRepository;
    this.resumeRepository = resumeRepository;
  }


  public void saveResume(JobSeekerId jobSeekerId,
                           Resume resume)
  {
    resumeRepository.saveResume(jobSeekerId,resume);
  }

  public void saveAsActive(JobSeekerId jobSeekerId,
                           Resume resume)
  {
    activeResumeRepository.makeActive(jobSeekerId, resume);
  }

  public Resume getActiveResume(JobSeekerId jobSeekerId)
  {
    return activeResumeRepository.activeResumeFor(jobSeekerId);
  }
}
