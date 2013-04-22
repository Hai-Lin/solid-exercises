package com.theladders.solid.ocp.jobseeker;


import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class ContactInfoConfidentialityProfile extends JobseekerConfidentialityProfile
{
  public ContactInfoConfidentialityProfile()
  {
    super();
  }

  public boolean resetContactInfoConfidentialFlags()
  {
    boolean isChanged = false;
    for (ConfidentialPhrase phrase : this.confidentialPhrases)
    {
      if (phrase.isConfidential())
      {
        phrase.setConfidential(false);
        isChanged = true;
      }
    }
    return isChanged;
  }
}
