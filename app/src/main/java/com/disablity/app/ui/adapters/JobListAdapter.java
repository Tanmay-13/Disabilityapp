package com.disablity.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobListAdapter extends RecyclerView.Adapter
{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    @Override
    public int getItemViewType(final int position) {
        return -1;
    }
}

