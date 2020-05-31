package com.disablity.app.ui.adapters;

import com.disablity.app.data.JobApplication;

public interface JobApplicationProvider
{
    public void bindData(JobApplicationAdapter.JobApplicationViewHolder viewHolder, JobApplication item);
}
