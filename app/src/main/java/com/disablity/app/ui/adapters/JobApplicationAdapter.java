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
import com.disablity.app.data.JobApplication;

public class JobApplicationAdapter extends ListAdapter<JobApplication, JobApplicationAdapter.JobApplicationViewHolder>
{

    private JobApplicationProvider provider;


    public JobApplicationAdapter(JobApplicationProvider provider)
    {

        super(new DiffUtil.ItemCallback<JobApplication>()
        {
            @Override
            public boolean areItemsTheSame(@NonNull JobApplication oldItem, @NonNull JobApplication newItem)
            {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull JobApplication oldItem, @NonNull JobApplication newItem)
            {
                return areItemsTheSame(oldItem, newItem);
            }
        });
        this.provider = provider;

    }

    @NonNull
    @Override
    public JobApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application, parent, false);
        return new JobApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobApplicationViewHolder holder, int position)
    {
        JobApplication item = getItem(position);
        holder.bindData(item,provider);
    }

    public static class JobApplicationViewHolder extends RecyclerView.ViewHolder
    {

        public final TextView applicationName;
        public final TextView applicationPosition;
        public final Button acceptButton;
        public final Button rejectButton;
        public final TextView applicationStatus;

        public JobApplicationViewHolder(@NonNull View itemView)
        {
            super(itemView);
            applicationName = itemView.findViewById(R.id.appliacation_namr);
            applicationPosition = itemView.findViewById(R.id.application_position);
            acceptButton = itemView.findViewById(R.id.application_accept_butt);
            rejectButton = itemView.findViewById(R.id.appliaction_reject_butt);
            applicationStatus = itemView.findViewById(R.id.application_status);
        }

        public void bindData(JobApplication item, JobApplicationProvider provider)
        {
            provider.bindData(this, item);
        }
    }
}
