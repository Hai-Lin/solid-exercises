package com.theladders.solid.srp.view;


import com.theladders.solid.srp.http.HttpResponse;

public interface Renderer
{
  public void renderSuccessfulView(HttpResponse response);
  public void renderFailedView(HttpResponse response);
  public void renderInvalidJobView(HttpResponse response, String jobId);
  public void renderInvalidResumeView(HttpResponse response);
  public void renderSuccessButProfileIncompleteView(HttpResponse response);
}
