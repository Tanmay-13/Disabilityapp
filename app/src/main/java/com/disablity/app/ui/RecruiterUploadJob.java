package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.disablity.app.R;
import com.disablity.app.data.JobProfile;

import java.util.ArrayList;
import java.util.Arrays;

import static com.disablity.app.AppUtil.gson;

public class RecruiterUploadJob extends AppCompatActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_upload_job);
    }
}