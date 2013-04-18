package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class JobApplicationResultViewProvider
{
  private final JobSearchService jobSearchService;

  private final HashMap<JobApplicationResults, JobApplicationResultView> resultMap;
  private final JobApplicationManager                                    jobApplicationManager;


  JobApplicationResultViewProvider(JobSearchService jobSearchService,
                                   ResumeManager resumeManager,
                                   MyResumeManager myResumeManager,
                                   JobApplicationSystem jobApplicationSystem,
                                   JobseekerProfileManager jobseekerProfileManager)
  {
    this.resultMap = new HashMap<>();
    setResultMap();
    this.jobSearchService = jobSearchService;
    this.jobApplicationManager = new JobApplicationManager(jobseekerProfileManager,
                                                     jobApplicationSystem,
                                                     resumeManager,
                                                     myResumeManager);
  }


  private void setResultMap()
  {
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    JobApplicationResultView success = new JobApplicationResultView("success", model);
    this.resultMap.put(JobApplicationResults.SUCCESS, success);
    JobApplicationResultView resumeNotComplete = new JobApplicationResultView("completeResumePlease", model);
    this.resultMap.put(JobApplicationResults.RESUME_NOT_COMPLETE, resumeNotComplete);
    errList.add("We could not process your application.");
    JobApplicationResultView error = new JobApplicationResultView("error", model, errList);
    errList.clear();
    this.resultMap.put(JobApplicationResults.INVALID, error);
  }


  private JobApplicationResultView getJobNonExistResult(String jobIdString)
  {

    Map<String, Object> model = new HashMap<>();
    model.put("jobId", Integer.parseInt(jobIdString));
    return new JobApplicationResultView("invalidJob", model);
  }


  public JobApplicationResultView provideApplicationResult(JobApplicationInfo jobApplicationInfo)
  {
    JobApplicationResults resultState = jobApplicationManager.getApplicationResult(jobApplicationInfo);
    if (resultState == JobApplicationResults.JOB_NOT_FOUND)
    {
      return getJobNonExistResult(jobApplicationInfo.getJobId());
    }
    return this.resultMap.get(resultState);

  }
}
