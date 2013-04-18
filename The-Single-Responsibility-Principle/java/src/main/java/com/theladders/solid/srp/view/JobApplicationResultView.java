package com.theladders.solid.srp.view;

import java.util.List;
import java.util.Map;

public class JobApplicationResultView
{
  private final String type;
  private final Map<String, Object> model;
  private final List<String> errorList;

  public JobApplicationResultView(String type,
                                  Map<String, Object> model)
  {
    this.type  = type;
    this.model = model;
    this.errorList = null;
  }

  public JobApplicationResultView(String type,
                                  Map<String, Object> model,
                                  List<String> errList)
  {
    this.type  = type;
    this.model = model;
    this.errorList = errList;
  }

  public String getType()
  {
    return type;
  }

  @Override
  public String toString()
  {
    return "Result [type=" + type + ", model=" + model + ", errorList=" + errorList + "]";
  }
}

