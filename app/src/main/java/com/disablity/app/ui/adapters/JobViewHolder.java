package com.disablity.app.ui.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.disablity.app.R;
import com.disablity.app.data.JobProfile;
import com.google.android.material.snackbar.Snackbar;

public class JobViewHolder extends RecyclerView.ViewHolder
{

    public TextView location;
    public TextView profile;
    public TextView salary;
    public Button applyButton;

    public JobViewHolder(@NonNull View itemView)
    {
        super(itemView);
        location = itemView.findViewById(R.id.job_location);
        profile = itemView.findViewById(R.id.job_profile);
        salary = itemView.findViewById(R.id.job_salary);
        applyButton = itemView.findViewById(R.id.job_apply);
    }

    public void bindData(JobProfile jobProfile, boolean isRecuiter, JobProfileProvider profileProvider)
    {
        location.setText(jobProfile.getLocation());
        profile.setText(jobProfile.getPosition());
        salary.setText(jobProfile.getSalary());
        profileProvider.bindData(this, jobProfile);
    }
}
