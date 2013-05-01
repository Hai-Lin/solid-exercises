package com.theladders.solid.srp.result;


import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.Renderer;

public class FailedApplicationResult implements Result
{


  @Override public boolean failure()
  {
    return true;
  }


  @Override public void render(HttpResponse response,
                               Renderer render)
  {
    render.renderFailedView(response);
  }
}
