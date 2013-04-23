package com.theladders.solid.lsp;

import java.util.HashMap;

public class Environment
{
  public static final String KEY_EMAIL_DOMAIN = "emaildomain";
  private final EnvData envData;

  public Environment()
  {
    this.envData = new EnvData();
  }

  public Environment(HashMap<String, String> hashMap)
  {
    this.envData = new EnvData(hashMap);
  }

  public String get(String key)
  {
    if(key == null)
      throw new NullPointerException("Environment: Key cannot be null when get");
    return this.envData.get(key);
  }

  public void put(String key, String value)
  {
    if(key == null || value == null)
      throw new NullPointerException("Environment: Key or value cannot be null when put");
    this.envData.put(key, value);
  }

  //Legacy code
  public String getAdminEmail()
  {
    String user = get("admin");
    String domain = get(KEY_EMAIL_DOMAIN);

    return user.length() > 0 && domain.length() > 0 ? user + "@" + domain : "";
  }

}
