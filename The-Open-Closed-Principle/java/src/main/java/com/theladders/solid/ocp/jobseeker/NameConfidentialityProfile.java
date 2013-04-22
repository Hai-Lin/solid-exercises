package com.theladders.solid.ocp.jobseeker;


import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class NameConfidentialityProfile extends JobseekerConfidentialityProfile
{
  public NameConfidentialityProfile()
  {
    super();
  }


  public boolean resetContactInfoConfidentialFlags()
  {
    return false;
  }


  public boolean resetConfidentialFlags()
  {
    return super.resetFlags();
  }
}


