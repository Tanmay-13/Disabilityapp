package com.disablity.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.disablity.app.util.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobApplication;
import com.disablity.app.data.JobProfile;
import com.disablity.app.ui.adapters.JobListAdapter;
import com.disablity.app.ui.adapters.JobProfileProvider;
import com.disablity.app.ui.adapters.JobViewHolder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ApplicantJobSearchActivity extends AppCompatActivity implements JobProfileProvider
{

    JobListAdapter jobListAdapter;
    RecyclerView list;
    ArrayList<JobProfile> jobProfiles;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra(AppUtil.USER_ID);
        setContentView(R.layout.activity_job_search);
        final SearchView searchView = findViewById(R.id.job_search_field);
        jobListAdapter = new JobListAdapter(false,this);
        list = findViewById(R.id.job_list);
        setUpList();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
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
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {
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
                return true;
            }
        });
    }

    private void setUpList()
    {
        list.setAdapter(jobListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        jobProfiles = AppUtil.getJobs(this);
        jobListAdapter.submitList(jobProfiles);
        jobListAdapter.notifyDataSetChanged();
    }


    @Override
    public void bindData(JobViewHolder holder, final JobProfile profile)
    {
        Button applyButton=holder.applyButton;
        applyButton.setOnClickListener(v -> {
            Snackbar.make(list, "Applied for the Job", Snackbar.LENGTH_LONG).show();
            v.setVisibility(View.INVISIBLE);

            JobApplication application=new JobApplication(profile.getRecuruiter_id(),id,profile.getId(),JobApplication.WAITING);
            AppUtil.addApplication(ApplicantJobSearchActivity.this, application);

            jobProfiles.remove(profile);
            jobListAdapter.submitList(jobProfiles);
        });

    }
}
