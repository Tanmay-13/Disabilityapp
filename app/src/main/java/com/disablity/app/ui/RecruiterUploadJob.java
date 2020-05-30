package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.disablity.app.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.JobProfile;

import static com.disablity.app.AppUtil.saveJob;

public class RecruiterUploadJob extends AppCompatActivity
{


    private TextView jobProfile;
    private TextView jobLocation;
    private TextView jobSalary;
    private String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra(AppUtil.USER_ID);


        setContentView(R.layout.activity_recruiter_upload_job);
        Button uploadJob = findViewById(R.id.upload_job_button);
        jobProfile = findViewById(R.id.job_profile_field);
        jobLocation = findViewById(R.id.job_location_field);
        jobSalary = findViewById(R.id.job_salary_field);
        uploadJob.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insertJob();
                finish();
            }
        });
    }

    public void insertJob()
    {
        String positon = jobProfile.getText().toString();
        String salary = jobSalary.getText().toString();
        String location = jobLocation.getText().toString();
        JobProfile profile = new JobProfile(positon, salary, location, id);
        Toast.makeText(this,"Saving sucessfull "+positon,Toast.LENGTH_SHORT).show();
//        Inter
        saveJob(this, profile);
    }
}