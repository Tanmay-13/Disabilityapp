package com.disablity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name = getIntent().getStringExtra(AppUtil.USER_ID);
        TextView userName=findViewById(R.id.user_name);
        userName.setText(name);
    }
}
