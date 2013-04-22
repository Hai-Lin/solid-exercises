package com.theladders.solid.ocp.jobseeker.ConfidentialityCategory;


import java.util.ArrayList;

public class ConfidentialityCategoryManager
{
  private final ArrayList<ConfidentialityCategory> confidentialityCategories;


  public ConfidentialityCategoryManager()
  {
    this.confidentialityCategories = new ArrayList<>();
  }


  public ConfidentialityCategory getConfidentialityCategory(String name)
  {
    for (ConfidentialityCategory category : this.confidentialityCategories)
    {
      if (category.getName().equals(name))
      {
        return category;
      }
    }
    return null;
  }


  private boolean isCategoryExist(String name)
  {
    for (ConfidentialityCategory category : this.confidentialityCategories)
    {
      if (category.getName().equals(name))
      {
        return true;
      }
    }
    return false;
  }


  public void createNewCategory(String name)
  {
    if (isCategoryExist(name))
    {
      throw new IllegalArgumentException("Name is already existed!");
    }
    ConfidentialityCategory confidentialityCategory = new ConfidentialityCategory(name);
    this.confidentialityCategories.add(confidentialityCategory);
  }


  public void addTypeToCategory(int typeId,
                                String name)
  {
    ConfidentialityCategory confidentialityCategory = getConfidentialityCategory(name);
    if (confidentialityCategory != null)
    {
      confidentialityCategory.addConfidentialityPhraseCategoryId(typeId);
    }
  }


}

