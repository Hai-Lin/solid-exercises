package com.theladders.solid.srp.resume;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.jobseeker.JobSeekerId;


public class ActiveResumeRepository
{
  private final Map<Integer, Resume> resumes;

  public ActiveResumeRepository()
  {
    this.resumes = new HashMap<>();
  }

  public Resume activeResumeFor(JobSeekerId jobSeekerId)
  {
    return resumes.get(jobSeekerId.getInt());
  }

  public void makeActive(JobSeekerId jobSeekerId, Resume resume)
  {
    resumes.put(jobSeekerId.getInt(), resume);
  }
}
