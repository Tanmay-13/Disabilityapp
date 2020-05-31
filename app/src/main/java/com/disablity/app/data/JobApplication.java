package com.disablity.app.data;

import com.disablity.app.ui.adapters.JobApplicationProvider;

public class JobApplication
{
    public static final int ACCEPTED=1;
    public static final int WAITING=2;
    public static final int REJECTED=3;

    private String recruiter;
    private String applicant;
    private String jobProfile;
    private int status;
    private long id;

    public JobApplication(String recruiter, String applicant,String  jobProfile, int status)
    {
        this.recruiter = recruiter;
        this.applicant = applicant;
        this.status = status;
        this.jobProfile=jobProfile;
        this.id=System.currentTimeMillis();
    }

    public String getRecruiter()
    {
        return recruiter;
    }

    public void setRecruiter(String recruiter)
    {
        this.recruiter = recruiter;
    }

    public String getApplicant()
    {
        return applicant;
    }

    public void setApplicant(String applicant)
    {
        this.applicant = applicant;
    }

    public int getStatus()
    {
        return status;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }



    public String getJobProfile()
    {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile)
    {
        this.jobProfile = jobProfile;
    }
}
