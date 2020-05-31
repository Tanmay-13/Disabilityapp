package com.disablity.app.ui.adapters;

import com.disablity.app.data.JobProfile;

public interface JobProfileProvider
{
    public void bindData(JobViewHolder holder, JobProfile profile);
}
