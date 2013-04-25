package com.theladders.solid.srp.jobseeker;


public class JobSeekerId
{
  private final int id;

  public JobSeekerId(int id)
  {
    this.id = id;
  }

  public int getInt()
  {
    return this.id;
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
    JobSeekerId other = (JobSeekerId) obj;
    return id == other.getInt();
  }

}