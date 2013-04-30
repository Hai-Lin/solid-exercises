package com.theladders.solid.srp.result;


import com.theladders.solid.srp.applicationInfo.JobApplicationInfo;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;
import com.theladders.solid.srp.view.Renderer;

public class InvalidResume   implements Result
{
  private final JobApplicationInfo jobApplicationInfo;

  public InvalidResume(JobApplicationInfo jobApplicationInfo)
  {
    this.jobApplicationInfo = jobApplicationInfo;
  }
  @Override public boolean failure()
  {
    return true;
  }


  @Override public void render(HttpResponse response,
                               Renderer render)
  {
    render.renderInvalidResumeView(response);
  }
}
