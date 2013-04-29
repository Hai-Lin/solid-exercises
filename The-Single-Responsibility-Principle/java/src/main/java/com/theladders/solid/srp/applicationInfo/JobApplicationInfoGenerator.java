package com.theladders.solid.srp.applicationInfo;


import java.util.HashMap;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.ResumeController;

public class JobApplicationInfoGenerator
{
  private final JobSearchService jobSearchService;
  private final ResumeController resumeController;


  public JobApplicationInfoGenerator(JobSearchService jobSearchService,
                                     ResumeManager resumeManager)
  {
    this.jobSearchService = jobSearchService;
    this.resumeController = new ResumeController(resumeManager);
  }


  public JobApplicationInfo processJobApplicationRequest(HttpRequest request)
  {
    String jobIdString = request.getParameter("jobId");
    JobSeeker jobSeeker = request.getSession().getJobSeeker();
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    HashMap<String, String> resumeInfoHash = new HashMap<>();
    resumeInfoHash.put("resumeName", resumeName);
    resumeInfoHash.put("whichResumeString", whichResumeString);
    resumeInfoHash.put("makeResumeActiveString", makeResumeActiveString);
    Job job = this.jobSearchService.getJob(Integer.parseInt(jobIdString));
    ResumeInfo resumeInfo = new ResumeInfo(resumeInfoHash);
    Resume resume = resumeController.processResume(resumeInfo,
                                                   jobSeeker);

    return new JobApplicationInfo(jobIdString, jobSeeker, resume, job);
  }
}