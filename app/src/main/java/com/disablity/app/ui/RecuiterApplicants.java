package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.disablity.app.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobProfile;
import com.disablity.app.ui.adapters.JobListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import static com.disablity.app.AppUtil.getJobs;
import static com.disablity.app.AppUtil.gson;

public class RecuiterApplicants extends AppCompatActivity
{
    private RecyclerView jobsList;
    private JobListAdapter jobListAdapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra(AppUtil.USER_ID);
        setContentView(R.layout.activity_recuiter_applicants);
        jobsList = findViewById(R.id.job_open_list);
        setUpList();
    }

    private void setUpList()
    {
        jobListAdapter = new JobListAdapter(true);
        jobsList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<JobProfile> jobs = getJobs(this);
        jobListAdapter.submitList(jobs);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}