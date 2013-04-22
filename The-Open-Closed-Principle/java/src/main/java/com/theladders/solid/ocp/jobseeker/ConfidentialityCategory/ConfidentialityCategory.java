package com.theladders.solid.ocp.jobseeker.ConfidentialityCategory;


import java.util.ArrayList;

public class ConfidentialityCategory
{
  private final String             name;
  private final ArrayList<Integer> confidentialityPhraseCategoryIds;


  public ConfidentialityCategory(String name)
  {
    this.name = name;
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


  public String getName()
  {
    return name;
  }

}
