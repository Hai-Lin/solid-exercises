package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.JobApplicationResultStatus;

public class JobApplicationResultViewGenerator
{

  public JobApplicationResultView generateJobApplicationResultView(JobApplicationInfo jobApplicationInfo,
                                                                   JobApplicationResultStatus jobApplicationResultStatus)
  {
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    if (jobApplicationResultStatus == JobApplicationResultStatus.JOB_NOT_FOUND)
    {
      model.put("jobId", Integer.parseInt(jobApplicationInfo.getJobId()));
      return new JobApplicationResultView("invalidJob", model);
    }

    if (jobApplicationResultStatus == JobApplicationResultStatus.SUCCESS)
    {
      return new JobApplicationResultView("success", model);
    }
    if (jobApplicationResultStatus == JobApplicationResultStatus.INVALID)
    {
      errList.add("We could not process your application.");
      return new JobApplicationResultView("error", model, errList);
    }

    return new JobApplicationResultView("completeResumePlease", model);

  }
}
