package com.theladders.solid.srp.http;

import com.theladders.solid.srp.JobApplicationResultView;

public class HttpResponse
{
  private JobApplicationResultView jobApplicationResultView;


  public String getResultType()
  {
    return jobApplicationResultView.getType();
  }


  public void setJobApplicationResultView(JobApplicationResultView jobApplicationResultView)
  {
    this.jobApplicationResultView = jobApplicationResultView;
  }
}
