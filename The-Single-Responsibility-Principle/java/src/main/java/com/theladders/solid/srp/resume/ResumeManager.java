package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.jobseeker.JobSeeker;

public class ResumeManager
{
  private final ResumeRepository resumeRepository;
  private final ActiveResumeRepository activeResumeRepository;


  public ResumeManager(ResumeRepository resumeRepository, ActiveResumeRepository activeResumeRepository)
  {
    this.activeResumeRepository = activeResumeRepository;
    this.resumeRepository = resumeRepository;
  }


  public void saveResume(JobSeeker jobSeeker,
                           Resume resume)
  {
    resumeRepository.saveResume(jobSeeker.getId(),resume);
  }

  public void saveAsActive(JobSeeker jobSeeker,
                           Resume resume)
  {
    activeResumeRepository.makeActive(jobSeeker.getId(), resume);
  }

  public Resume getActiveResume(JobSeeker jobSeeker)
  {
    return activeResumeRepository.activeResumeFor(jobSeeker.getId());
  }
}
