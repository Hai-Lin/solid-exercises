package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResultProvider
{
  private final JobSearchService jobSearchService;

  private final HashMap<ApplicationResultState, Result> resultMap;
  private final ApplicationManager                      applicationManager;


  ResultProvider(JobSearchService jobSearchService,
                 ResumeManager resumeManager,
                 MyResumeManager myResumeManager,
                 JobApplicationSystem jobApplicationSystem,
                 JobseekerProfileManager jobseekerProfileManager)
  {
    this.resultMap = new HashMap<>();
    setResultMap();
    this.jobSearchService = jobSearchService;
    this.applicationManager = new ApplicationManager(jobseekerProfileManager,
                                                     jobApplicationSystem,
                                                     resumeManager,
                                                     myResumeManager);
  }


  private void setResultMap()
  {
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    Result success = new Result("success", model);
    this.resultMap.put(ApplicationResultState.SUCCESS, success);
    Result resumeNotComplete = new Result("completeResumePlease", model);
    this.resultMap.put(ApplicationResultState.RESUME_NOT_COMPLETE, resumeNotComplete);
    errList.add("We could not process your application.");
    Result error = new Result("error", model, errList);
    errList.clear();
    this.resultMap.put(ApplicationResultState.INVALID, error);
  }


  private Result getJobNonExistResult(String jobIdString)
  {

    Map<String, Object> model = new HashMap<>();
    model.put("jobId", Integer.parseInt(jobIdString));
    return new Result("invalidJob", model);
  }


  public Result provideApplicationResult(String jobIdString,
                                         Jobseeker jobSeeker,
                                         String resumeName,
                                         String whichResumeString,
                                         String makeResumeActiveString)
  {
    Job job = jobSearchService.getJob(Integer.parseInt(jobIdString));
    ApplicationResultState resultState = applicationManager.getApplicationResult(jobSeeker,
                                                                                   resumeName,
                                                                                   job,
                                                                                   whichResumeString,
                                                                                   makeResumeActiveString);
    if (resultState == ApplicationResultState.JOB_NOT_FOUND)
    {
      return getJobNonExistResult(jobIdString);
    }
    return this.resultMap.get(resultState);

  }
}
