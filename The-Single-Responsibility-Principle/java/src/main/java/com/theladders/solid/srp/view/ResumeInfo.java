package com.theladders.solid.srp.view;


import java.util.HashMap;
public class ResumeInfo
{
  private String name;
  private boolean isExisting;
  private boolean isMakdeActive;
  public ResumeInfo(HashMap<String, String> info)
  {
    this.name = info.get("resumeName");
    this.isExisting = info.get("whichResumeString").equals("existing");
    this.isMakdeActive = info.get("makeResumeActiveString").equals("yes");
  }

}
