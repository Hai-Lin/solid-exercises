package com.theladders.solid.lsp;

// Just a stub. Nothing to see here.

import java.util.HashMap;

public class EnvironmentFactory
{
  public static HashMap<String, String> getEnvironmentFor(String hostName)
  {
    HashMap<String, String> env = new HashMap<>();

    env.put("isSSL", "true");
    env.put("home", "http://" + hostName);
    env.put("secureHome", "https://" + hostName);

    return env;
  }
}
