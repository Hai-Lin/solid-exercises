package com.theladders.solid.srp.result;


import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.JobApplicationResultViewFactory;

public interface Result
{
  boolean failure();
  public void render(HttpResponse response, JobApplicationResultViewFactory render);
}
