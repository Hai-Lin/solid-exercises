package com.theladders.solid.srp.result;


import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.view.Renderer;

public class InvalidJob  implements Result
{

  private final String jobIdString;

  public InvalidJob(String jobIdString)
  {
    this.jobIdString = jobIdString;
  }

  @Override public boolean failure()
  {
    return true;
  }


  @Override public void render(HttpResponse response, Renderer render)
  {

    render.renderInvalidJobView(response, jobIdString);
  }
}
