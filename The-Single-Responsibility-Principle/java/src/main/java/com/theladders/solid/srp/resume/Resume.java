package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.applicationInfo.ResumeInfo;

public class Resume
{
  private final String resumeName;


  public Resume(String resumeName)
  {
    if (isEmpty(resumeName))
    {
      throw new NullPointerException("bad resume name");
    }

    this.resumeName = resumeName;
  }


  public Resume(ResumeInfo resumeInfo)
  {
    String name = resumeInfo.getName();
    if (isEmpty(name))
    {
      throw new NullPointerException("bad resume name");
    }
    this.resumeName = name;
  }


  private boolean isEmpty(String string)
  {
    return string == null || string.equals("");
  }


  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((resumeName == null) ? 0 : resumeName.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    Resume other = (Resume) obj;
    if (resumeName == null)
    {
      if (other.resumeName != null)
      {
        return false;
      }
    }
    else if (!resumeName.equals(other.resumeName))
    {
      return false;
    }
    return true;
  }
}
