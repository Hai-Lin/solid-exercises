package com.theladders.solid.srp.applicationInfo;


import java.util.HashMap;

public class ResumeInfo
{
  private String  name;
  private boolean isExisting;
  private boolean isMakdeActive;


  public ResumeInfo(HashMap<String, String> info)
  {
    this.name = info.get("resumeName");
    this.isExisting = validString(info.get("whichResumeString")).equals("existing");
    this.isMakdeActive = validString(info.get("makeResumeActiveString")).equals("yes");
  }


  private String validString(String string)
  {
    if (string == null)
    {
      return "";
    }
    return string;
  }


  public boolean isExisting()
  {
    return this.isExisting;
  }


  public boolean isMakeActive()
  {
    return this.isMakdeActive;
  }


  public String getName()
  {
    return name;
  }


}
