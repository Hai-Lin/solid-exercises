
package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.ApplicationResultSatate;


/**
 * Created with IntelliJ IDEA.
 * User: hlin
 * Date: 4/17/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationManager
{
  private final Job job;
  private final Resume resume;
  private final Jobseeker jobseeker;


  public ApplicationManager(Job job, Resume resume, Jobseeker jobseeker)
  {
    this.job = job;
    this.resume = resume;
    this.jobseeker = jobseeker;
  }

  public final ApplicationResultSatate getApplicationResult()
  {
    if(this.job == null)
      return ApplicationResultSatate.JOB_NOT_FOUND;



    return ApplicationResultSatate.SUCCESS;
  }


}
