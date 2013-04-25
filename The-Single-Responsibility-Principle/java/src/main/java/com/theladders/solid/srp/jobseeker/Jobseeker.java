package com.theladders.solid.srp.jobseeker;

public class JobSeeker
{
  private final JobSeekerId id;
  private final boolean     hasPremiumAccount;


  public JobSeeker(int id,
                   boolean hasPremiumAccount)
  {
    this.id = new JobSeekerId(id);
    this.hasPremiumAccount = hasPremiumAccount;
  }


  public boolean isPremium()
  {
    return hasPremiumAccount;
  }


  public JobSeekerId getId()
  {
    return id;
  }


  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + id.getInt();
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
    JobSeeker other = (JobSeeker) obj;
    return id.equals(other.getId());
  }
}
