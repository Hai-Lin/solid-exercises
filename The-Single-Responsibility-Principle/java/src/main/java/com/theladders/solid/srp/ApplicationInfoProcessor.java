package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ApplicationInfoProcessor
{
    public ApplicationInfo processRequest(HttpRequest request)
    {
        String jobIdString = request.getParameter("jobId");
        Jobseeker jobseeker = request.getSession().getJobseeker();
        String makeResumeActiveString = request.getParameter("makeResumeActive");
        String resumeName = request.getParameter("resumeName");
        String whichResumeString = request.getParameter("whichResume");
        return new ApplicationInfo(jobIdString,
                                   jobseeker,
                                   makeResumeActiveString,
                                   resumeName,
                                   whichResumeString);
    }
}
