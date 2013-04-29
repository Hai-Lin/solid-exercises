package com.theladders.solid.srp.result;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public class SuccessApplication implements Result
{
  private final JobApplicationInfo jobApplicationInfo;
  public SuccessApplication(JobApplicationInfo jobApplicationInfo)
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
