package com.theladders.solid.ocp.jobseeker.ConfidentialityCategory;


import java.util.ArrayList;

public class ConfidentialityCategory
{
  private final int                id;
  private final String             name;
  private final ArrayList<Integer> confidentialityPhraseCategoryIds;


  public ConfidentialityCategory(int id,
                                 String name)
  {
    this.name = name;
    this.id = id;
    this.confidentialityPhraseCategoryIds = new ArrayList<>();
  }


  public void addConfidentialityPhraseCategoryId(int id)
  {
    confidentialityPhraseCategoryIds.add(id);
  }


  public ArrayList<Integer> getConfidentialityPhraseCategoryIds()
  {
    return this.confidentialityPhraseCategoryIds;
  }


  public int getCategoryId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

}
