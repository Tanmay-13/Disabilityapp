package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.disablity.app.R;
import com.disablity.app.ui.login.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new  Thread(() -> start()).start();
    }

    private  void start(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}