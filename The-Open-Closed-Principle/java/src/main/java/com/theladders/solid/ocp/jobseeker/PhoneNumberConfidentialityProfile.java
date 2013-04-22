package com.theladders.solid.ocp.jobseeker;


import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class PhoneNumberConfidentialityProfile extends JobseekerConfidentialityProfile
{
  public PhoneNumberConfidentialityProfile()
  {
    super();
  }


  public boolean resetContactInfoConfidentialFlags()
  {
    return super.resetFlags();
  }


  public boolean resetConfidentialFlags()
  {
    return super.resetFlags();
  }
}
