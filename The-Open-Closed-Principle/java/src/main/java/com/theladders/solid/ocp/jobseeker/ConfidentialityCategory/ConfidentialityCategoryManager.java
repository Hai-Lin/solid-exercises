package com.theladders.solid.ocp.jobseeker.ConfidentialityCategory;


import java.util.ArrayList;

public class ConfidentialityCategoryManager
{
  private final ArrayList<ConfidentialityCategory> confidentialityCategories;


  public ConfidentialityCategoryManager()
  {
    this.confidentialityCategories = new ArrayList<>();
  }


  public ConfidentialityCategory getConfidentialityCategoryById(int id)
  {
    for (ConfidentialityCategory category : this.confidentialityCategories)
    {
      if (category.getCategoryId() == id)
      {
        return category;
      }
    }

    return null;

  }


  public void createNewCategory(String name)
  {
    ConfidentialityCategory confidentialityCategory = new ConfidentialityCategory(confidentialityCategories.size(),
                                                                                  name);
  }


  public void addTypeToCategory(int typeId,
                                int categoryId)
  {
    ConfidentialityCategory confidentialityCategory = getConfidentialityCategoryById(categoryId);
    if (confidentialityCategory != null)
    {
      confidentialityCategory.addConfidentialityPhraseCategoryId(typeId);
    }
  }



}

