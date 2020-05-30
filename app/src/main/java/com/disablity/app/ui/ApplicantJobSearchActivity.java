package com.disablity.app.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    ArrayList<JobProfile> jobProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        final SearchView searchView = findViewById(R.id.job_search_field);
        jobListAdapter = new JobListAdapter(false);
        list = findViewById(R.id.job_list);
        setUpList();
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String query=searchView.getQuery().toString();
                if (query.trim().isEmpty()){
                    jobListAdapter.submitList(jobProfiles);
                }else {
                    ArrayList<JobProfile>  list=new ArrayList<>();
                    for (JobProfile profile :jobProfiles){
                        if (profile.toString().contains(query)){
                            list.add(profile);
                        }
                    }
                    jobListAdapter.submitList(list);
                }
            }
        });
    }

    private void setUpList()
    {
        list.setAdapter(jobListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        jobProfiles = AppUtil.getJobs(this);
        jobListAdapter.submitList(jobProfiles);
    }


}
