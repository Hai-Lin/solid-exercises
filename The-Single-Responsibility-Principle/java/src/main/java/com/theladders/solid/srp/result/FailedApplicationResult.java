package com.theladders.solid.srp.result;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public class FailedApplicationResult implements Result
{
  private final JobApplicationInfo jobApplicationInfo;


  public FailedApplicationResult(JobApplicationInfo jobApplicationInfo)
  {
    this.jobApplicationInfo = jobApplicationInfo;
  }


  @Override public boolean failure()
  {
    return true;
  }


  @Override public void render(HttpResponse response,
                               JobApplicationResultViewFactory render)
  {
  }
}
