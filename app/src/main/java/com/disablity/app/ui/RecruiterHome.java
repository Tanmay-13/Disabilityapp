package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.disablity.app.AppUtil;
import com.disablity.app.R;

public class RecruiterHome extends AppCompatActivity
{
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra(AppUtil.USER_ID);

        setContentView(R.layout.activity_recruter_home);
        Button uploadJob=findViewById(R.id.upload_job);
        uploadJob.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent in=new Intent(RecruiterHome.this,RecruiterUploadJob.class);
                in.putExtra(AppUtil.USER_ID,id);
                startActivity(in);
                finish();
            }
        });
        Button applicants=findViewById(R.id.job_applicants);
        applicants.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(RecruiterHome.this,RecuiterApplicants.class);
                startActivity(in);
                finish();
            }
        });
    }
}