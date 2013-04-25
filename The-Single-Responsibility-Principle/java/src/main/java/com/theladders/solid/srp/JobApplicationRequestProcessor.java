package com.theladders.solid.srp;


import java.util.HashMap;

import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.JobSeeker;
import com.theladders.solid.srp.job.Job;

public class JobApplicationRequestProcessor
{
  private final JobSearchService jobSearchService;


  public JobApplicationRequestProcessor(JobSearchService jobSearchService)
  {
    this.jobSearchService = jobSearchService;
  }


  public JobApplicationInfo processJobApplicationRequest(HttpRequest request)
  {
    String jobIdString = request.getParameter("jobId");
    JobSeeker jobSeeker = request.getSession().getJobSeeker();
    String makeResumeActiveString = request.getParameter("makeResumeActive");
    String resumeName = request.getParameter("resumeName");
    String whichResumeString = request.getParameter("whichResume");
    HashMap<String, String> resumeInfo = new HashMap<>();
    resumeInfo.put("resumeName", resumeName);
    resumeInfo.put("whichResumeString", whichResumeString);
    resumeInfo.put("makeResumeActiveString", makeResumeActiveString);
    Job job = this.jobSearchService.getJob(Integer.parseInt(jobIdString));
    return new JobApplicationInfo(jobIdString, jobSeeker, resumeInfo, job);
  }
}
