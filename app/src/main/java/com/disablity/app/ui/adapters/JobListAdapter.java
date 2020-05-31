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
    private JobProfileProvider profileProvider;

    public JobListAdapter(boolean isRecuiter,JobProfileProvider profileProvider)
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
        this.profileProvider=profileProvider;
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
        holder.bindData(item, isRecuiter,profileProvider);
    }
}

