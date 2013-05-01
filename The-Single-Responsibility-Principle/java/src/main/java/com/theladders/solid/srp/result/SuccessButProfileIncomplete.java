package com.theladders.solid.srp.result;


import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.Renderer;

public class SuccessButProfileIncomplete implements Result
{
  @Override public boolean failure()
  {
    return true;
  }


  @Override public void render(HttpResponse response,
                               Renderer render)
  {
    render.renderSuccessButProfileIncompleteView(response);
  }
}
