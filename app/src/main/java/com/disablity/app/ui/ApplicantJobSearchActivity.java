package com.disablity.app.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.disablity.app.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobProfile;
import com.disablity.app.ui.adapters.JobListAdapter;

import java.util.ArrayList;

public class ApplicantJobSearchActivity extends AppCompatActivity
{

    JobListAdapter jobListAdapter;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        jobListAdapter = new JobListAdapter();
        list = findViewById(R.id.job_list);
        list.setAdapter(jobListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        setUpAdapter();
    }


    public void setUpAdapter()
    {
        ArrayList<JobProfile> getjobs = AppUtil.getjobs(this);
        jobListAdapter.submitList(getjobs);
    }
}
