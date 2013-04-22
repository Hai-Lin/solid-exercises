package com.theladders.solid.ocp.jobseeker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.jobseeker.ConfidentialityCategory.ConfidentialityCategory;
import com.theladders.solid.ocp.jobseeker.ConfidentialityCategory.ConfidentialityCategoryManager;
import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public abstract class JobseekerConfidentialityProfile
{
  private final HashMap<Integer, List<ConfidentialPhrase>> confidentialPhrases;
  private final ConfidentialityCategoryManager             confidentialityCategoryManager;


  public JobseekerConfidentialityProfile()
  {
    this.confidentialPhrases = new HashMap<>();
    this.confidentialityCategoryManager = new ConfidentialityCategoryManager();
  }


  private List<ConfidentialPhrase> getPhrasesById(int id)
  {
    return this.confidentialPhrases.get(id);
  }


  public boolean resetFlagsById(int id)
  {
    boolean isChanged = false;
    List<ConfidentialPhrase> phrases = this.getPhrasesById(id);

    for (ConfidentialPhrase phrase : phrases)
    {
      if (phrase.isConfidential())
      {
        phrase.setConfidential(false);
        isChanged = true;
      }
    }
    return isChanged;
  }


  public boolean resetConfidentialFlags()
  {
    Iterator it = confidentialPhrases.entrySet().iterator();
    boolean isChanged = false;


    for (Integer key : confidentialPhrases.keySet())
    {
      isChanged = resetFlagsById(key);
    }


    return isChanged;
  }


  public boolean resetConfidentialFlagsByCategoryId(int id)
  {
    boolean isChanged = false;

    ConfidentialityCategory confidentialityCategory = this.confidentialityCategoryManager.getConfidentialityCategoryById(
            id);
    for (Integer confidentialityPhraseCategoryId : confidentialityCategory.getConfidentialityPhraseCategoryIds())
    {
      if (resetFlagsById(confidentialityPhraseCategoryId))
      {
        isChanged = true;
      }
    }
    return isChanged;
  }


  public abstract boolean resetContactInfoConfidentialFlags();

}

