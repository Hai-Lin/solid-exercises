package com.theladders.solid.ocp.jobseeker;


import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public abstract class JobseekerConfidentialityProfile
{
  protected List<ConfidentialPhrase> confidentialPhrases;

  public JobseekerConfidentialityProfile()
  {
    this.confidentialPhrases = new ArrayList<>();
  }
  protected boolean resetFlags()
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

  public abstract boolean resetContactInfoConfidentialFlags();
  public abstract boolean resetConfidentialFlags();

}

