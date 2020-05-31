package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.disablity.app.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.User;
import com.disablity.app.ui.login.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ApplicantHome extends AppCompatActivity
{

    private User user;
    private Button searchJob;
    private String id;
    private FirebaseAuth mAuth;
    private TextView applicantName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_home);
        String obj = getIntent().getStringExtra(AppUtil.USER_OBJ);
        mAuth=FirebaseAuth.getInstance();


        user = AppUtil.gson.fromJson(obj, User.class);
        id = user.getId();
        applicantName = findViewById(R.id.applicant_name);
        applicantName.setText(user.getName());
        searchJob = findViewById(R.id.search_job);
        searchJob.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(ApplicantHome.this, ApplicantJobSearchActivity.class);
                in.putExtra(AppUtil.USER_ID, id);
                startActivity(in);
            }
        });
        Button pastApplication = findViewById(R.id.past_application);
        pastApplication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(ApplicantHome.this, ApplicantPastApplications.class);
                in.putExtra(AppUtil.USER_ID, id);
                startActivity(in);
            }
        });
        Button  logOut=findViewById(R.id.applicant_log_out);
        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                Intent intent=new Intent(ApplicantHome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}