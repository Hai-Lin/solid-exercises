package com.theladders.solid.srp.result;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public class SuccessfulApplicationResult implements Result
{
  private final JobApplicationInfo jobApplicationInfo;


  public SuccessfulApplicationResult(JobApplicationInfo jobApplicationInfo)
  {
    this.jobApplicationInfo = jobApplicationInfo;
  }


  @Override public boolean failure()
  {
    return false;
  }


  @Override public void render(HttpResponse response,
                               JobApplicationResultViewFactory render)
  {
  }
}
