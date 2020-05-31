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
import com.google.gson.Gson;

public class RecruiterHome extends AppCompatActivity
{
    private String id;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        String obj = getIntent().getStringExtra(AppUtil.USER_OBJ);
        User user = AppUtil.gson.fromJson(obj, User.class);
        id=user.getId();

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

            }
        });
        Button applicants=findViewById(R.id.job_applicants);
        applicants.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(RecruiterHome.this,RecuiterApplicants.class);
                in.putExtra(AppUtil.USER_ID,id);
                startActivity(in);

            }
        });
        TextView recuiterID=findViewById(R.id.recruiter_id);
        recuiterID.setText(user.getName());
        Button logOut=findViewById(R.id.logout_recuiter);
        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                Intent intent=new Intent(RecruiterHome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}