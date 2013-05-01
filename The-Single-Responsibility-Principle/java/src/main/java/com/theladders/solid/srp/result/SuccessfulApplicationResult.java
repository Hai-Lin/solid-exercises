package com.theladders.solid.srp.result;


import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.Renderer;

public class SuccessfulApplicationResult implements Result
{

  @Override public boolean failure()
  {
    return false;
  }


  @Override public void render(HttpResponse response,
                               Renderer render)
  {
    render.renderSuccessfulView(response);
  }
}
