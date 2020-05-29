package com.disablity.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disablity.app.R;
import com.disablity.app.data.JobProfile;

public class JobListAdapter extends ListAdapter<JobProfile, JobViewHolder>
{

    public JobListAdapter()
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
        holder.bindData(item);
    }
}

class JobViewHolder extends RecyclerView.ViewHolder
{
    public JobViewHolder(@NonNull View itemView)
    {
        super(itemView);
    }

    public void bindData(JobProfile jobProfile)
    {
        TextView location = itemView.findViewById(R.id.job_location);
        TextView profile = itemView.findViewById(R.id.job_profile);
        TextView salary = itemView.findViewById(R.id.job_salary);
        location.setText(jobProfile.getLocation());
        profile.setText(jobProfile.getPosition());
        salary.setText(jobProfile.getSalary());
    }
}

