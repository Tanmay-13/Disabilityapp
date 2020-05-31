package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.disablity.app.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobApplication;
import com.disablity.app.data.JobProfile;
import com.disablity.app.data.User;
import com.disablity.app.ui.adapters.JobApplicationAdapter;
import com.disablity.app.ui.adapters.JobApplicationProvider;
import com.disablity.app.ui.adapters.JobListAdapter;
import com.disablity.app.ui.adapters.JobProfileProvider;
import com.disablity.app.ui.adapters.JobViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.disablity.app.AppUtil.getApplications;
import static com.disablity.app.AppUtil.getJobs;
import static com.disablity.app.AppUtil.gson;

public class RecuiterApplicants extends AppCompatActivity implements JobApplicationProvider
{
    private RecyclerView jobsList;
    private JobApplicationAdapter jobApplicationAdapter;
    private ArrayList<JobProfile> jobProfiles;
    private ArrayList<User> userArrayList;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        jobProfiles = AppUtil.getJobs(this);
        userArrayList = AppUtil.getUsers(this);
        id = getIntent().getStringExtra(AppUtil.USER_ID);
        setContentView(R.layout.activity_recuiter_applicants);
        jobsList = findViewById(R.id.job_open_list);
        setUpList();
    }

    private void setUpList()
    {
        jobApplicationAdapter = new JobApplicationAdapter(this);
        jobsList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<JobApplication> jobs = getApplications(this);
        jobApplicationAdapter.submitList(jobs);
        jobsList.setAdapter(jobApplicationAdapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    @Override
    public void bindData(final JobApplicationAdapter.JobApplicationViewHolder viewHolder, final JobApplication item)
    {
        JobProfile jobProfile = null;
        for (JobProfile profile : jobProfiles) {
            if (profile.getId().equals(item.getJobProfile())) {
                jobProfile = profile;
            }
        }

        User applicant = null;
        for (User usr : userArrayList) {
            if (usr.getId().equals(item.getApplicant()))
                applicant = usr;
        }
        viewHolder.applicationName.setText(applicant.getName());
        viewHolder.applicationPosition.setText(jobProfile.getPosition());
        reviewStatus(viewHolder, item);
        viewHolder.acceptButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                item.setStatus(JobApplication.ACCEPTED);
                AppUtil.addApplication(RecuiterApplicants.this, item);
                reviewStatus(viewHolder, item);
            }
        });
        viewHolder.rejectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                item.setStatus(JobApplication.REJECTED);
                reviewStatus(viewHolder, item);
                AppUtil.addApplication(RecuiterApplicants.this, item);
            }
        });
    }

    private void reviewStatus(JobApplicationAdapter.JobApplicationViewHolder viewHolder, JobApplication item)
    {
        if (item.getStatus()!=JobApplication.WAITING){
            viewHolder.acceptButton.setVisibility(View.GONE);
            viewHolder.rejectButton.setVisibility(View.GONE);
        }

        switch (item.getStatus()) {
            case JobApplication.ACCEPTED:
                viewHolder.applicationStatus.setText("Accepted");
                break;
            case JobApplication.REJECTED:
                viewHolder.applicationStatus.setText("Rejected");
                break;
            case JobApplication.WAITING:
                viewHolder.applicationStatus.setText("Waiting");
                break;
        }
    }
}