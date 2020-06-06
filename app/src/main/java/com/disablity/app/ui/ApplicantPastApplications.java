package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.disablity.app.util.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobApplication;
import com.disablity.app.data.JobProfile;
import com.disablity.app.data.User;
import com.disablity.app.ui.adapters.JobApplicationAdapter;
import com.disablity.app.ui.adapters.JobApplicationProvider;

import java.util.ArrayList;

public class ApplicantPastApplications extends AppCompatActivity implements JobApplicationProvider
{

    private String id;
    private ArrayList<JobProfile> jobProfiles;
    private ArrayList<User> userArrayList;
    private JobApplicationAdapter applicationAdapter;
    private RecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_past_applications);
        id = getIntent().getStringExtra(AppUtil.USER_ID);
        jobProfiles = AppUtil.getJobs(this);
        userArrayList = AppUtil.getUsers(this);
        applicationAdapter = new JobApplicationAdapter(this);
        list = findViewById(R.id.past_applications);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(applicationAdapter);
        ArrayList<JobApplication> applications = AppUtil.getApplications(this);
        for (int i = 0; i < applications.size(); i++) {
            JobApplication application = applications.get(i);
            if (!application.getApplicant().equals(id)) {
                applications.remove(application);
            }

        }
        applicationAdapter.submitList(applications);
    }

    @Override
    public void bindData(JobApplicationAdapter.JobApplicationViewHolder viewHolder, JobApplication item)
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
        viewHolder.applicationPosition.setText(jobProfile.getPosition());
        viewHolder.applicationName.setText(applicant.getName());
        viewHolder.acceptButton.setVisibility(View.GONE);
        viewHolder.rejectButton.setVisibility(View.GONE);
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