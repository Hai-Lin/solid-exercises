package com.theladders.solid.lsp;

import java.util.HashMap;

public class EnvData
{
  private final HashMap<String, String> data;
  public EnvData()
  {
    this.data = new HashMap<>();
  }

  public EnvData(HashMap<String, String> hashMap)
  {
    if(hashMap == null)
      throw new NullPointerException("EnvData: Cannot initialize EnvData with Null pointer");
    this.data = hashMap;
  }


  public String get(String key)
  {
    if(key == null)
      throw new NullPointerException("EnvData: Key cannot be null when get");
    return this.data.get(key);
  }

  public void put(String key, String value)
  {
    if(key == null || value == null)
      throw new NullPointerException("EnvData: Key or value cannot be null when put");
    this.data.put(key, value);
  }

}
