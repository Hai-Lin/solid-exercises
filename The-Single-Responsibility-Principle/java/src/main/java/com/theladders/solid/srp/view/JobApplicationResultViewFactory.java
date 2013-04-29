package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.theladders.solid.srp.http.HttpResponse;

public class JobApplicationResultViewFactory implements Renderer
{


  @Override public void renderFailedView(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    errList.add("We could not process your application.");
    JobApplicationResultView resultView = new JobApplicationResultView("error", model, errList);
    response.setJobApplicationResultView(resultView);
  }


  @Override public void renderInvalidJobView(HttpResponse response,
                                             String jobId)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", jobId);
    JobApplicationResultView resultView = new JobApplicationResultView("invalidJob", model);
    response.setJobApplicationResultView(resultView);
  }


  @Override public void renderInvalidResumeView(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();
    errList.add("We could not process your application.");
    JobApplicationResultView resultView = new JobApplicationResultView("error", model, errList);
    response.setJobApplicationResultView(resultView);
  }


  @Override public void renderSuccessfulView(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    JobApplicationResultView resultView = new JobApplicationResultView("success", model);
    response.setJobApplicationResultView(resultView);
  }


  @Override public void renderSuccessButProfileIncompleteView(HttpResponse response)
  {
    Map<String, Object> model = new HashMap<>();
    JobApplicationResultView resultView = new JobApplicationResultView("completeResumePlease", model);
    response.setJobApplicationResultView(resultView);
  }
}
