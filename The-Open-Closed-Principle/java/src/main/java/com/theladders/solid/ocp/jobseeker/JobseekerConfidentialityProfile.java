package com.theladders.solid.ocp.jobseeker;


import java.util.HashMap;
import java.util.List;

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
    boolean isChanged = false;
    for (Integer key : confidentialPhrases.keySet())
    {
      isChanged = resetFlagsById(key);
    }


    return isChanged;
  }


  public boolean resetConfidentialFlagsByCategoryId(String name)
  {
    boolean isChanged = false;

    ConfidentialityCategory confidentialityCategory = this.confidentialityCategoryManager.getConfidentialityCategory(
            name);
    for (Integer confidentialityPhraseCategoryId : confidentialityCategory.getConfidentialityPhraseCategoryIds())
    {
      if (resetFlagsById(confidentialityPhraseCategoryId))
      {
        isChanged = true;
      }
    }
    return isChanged;
  }


  public void createNewConfidentialType(int id,
                                        List<ConfidentialPhrase> confidentialPhrasesList)
  {
    confidentialPhrases.put(id, confidentialPhrasesList);
  }


  public void addConfidentialTypeToCategory(int typeId,
                                            String categoryName)
  {
    confidentialityCategoryManager.addTypeToCategory(typeId, categoryName);
  }


  public void createNewCategory(String name)
  {
    confidentialityCategoryManager.createNewCategory(name);
  }


  public abstract boolean resetContactInfoConfidentialFlags();

}

