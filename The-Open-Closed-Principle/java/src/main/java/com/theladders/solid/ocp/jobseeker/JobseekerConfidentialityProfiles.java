package com.theladders.solid.ocp.jobseeker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;

public class JobseekerConfidentialityProfiles
{
  private List<JobseekerConfidentialityProfile> confidentialityProfile;


  public JobseekerConfidentialityProfiles()
  {
    confidentialityProfile = new ArrayList<>();
  }


  public boolean resetAllConfidentialFlags()
  {
    boolean isChanged = false;

    for (JobseekerConfidentialityProfile profile : this.confidentialityProfile)
    {
      if (profile.resetConfidentialFlags())
      {
        isChanged = true;
      }
    }
    return isChanged;
  }

  public boolean resetAllContactInfoConfidentialFlags()
  {
    boolean isChanged = false;

    for (JobseekerConfidentialityProfile profile : this.confidentialityProfile)
    {
      if (profile.resetContactInfoConfidentialFlags())
      {
        isChanged = true;
      }
    }
    return isChanged;
  }


}
