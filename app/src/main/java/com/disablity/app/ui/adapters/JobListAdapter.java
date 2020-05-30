package com.disablity.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disablity.app.R;
import com.disablity.app.data.JobProfile;
import com.google.android.material.snackbar.Snackbar;

public class JobListAdapter extends ListAdapter<JobProfile, JobViewHolder>
{
    private boolean isRecuiter;

    public JobListAdapter(boolean isRecuiter)
    {
        super(new DiffUtil.ItemCallback<JobProfile>()
        {
            @Override
            public boolean areItemsTheSame(@NonNull JobProfile oldItem, @NonNull JobProfile newItem)
            {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull JobProfile oldItem, @NonNull JobProfile newItem)
            {
                return oldItem.getId().equals(newItem.getId());
            }
        });
        this.isRecuiter = isRecuiter;
    }


    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position)
    {
        JobProfile item = getItem(position);
        holder.bindData(item, isRecuiter);
    }
}

class JobViewHolder extends RecyclerView.ViewHolder
{
    public JobViewHolder(@NonNull View itemView)
    {
        super(itemView);
    }

    public void bindData(JobProfile jobProfile, boolean isRecuiter)
    {
        TextView location = itemView.findViewById(R.id.job_location);
        TextView profile = itemView.findViewById(R.id.job_profile);
        TextView salary = itemView.findViewById(R.id.job_salary);
        Button applyButton = itemView.findViewById(R.id.job_apply);
        location.setText(jobProfile.getLocation());
        profile.setText(jobProfile.getPosition());
        salary.setText(jobProfile.getSalary());
        if (isRecuiter){
            applyButton.setVisibility(View.INVISIBLE);
        }
        applyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(itemView, "Applied for the Job", Snackbar.LENGTH_LONG).show();
                v.setVisibility(View.INVISIBLE);
            }
        });
    }
}

