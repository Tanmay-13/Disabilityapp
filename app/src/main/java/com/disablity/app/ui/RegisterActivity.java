package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.disablity.app.AppUtil;
import com.disablity.app.MainActivity;
import com.disablity.app.R;
import com.disablity.app.data.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.disablity.app.AppUtil.gson;

public class RegisterActivity extends AppCompatActivity
{

   private EditText nameField;
   private EditText ageField;
   private EditText addressField;
   private Button registerButton;
   private Spinner userType;
   private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        id = getIntent().getStringExtra(AppUtil.USER_ID);
        checkAlredyRegistered();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameField = findViewById(R.id.name_field);
        ageField = findViewById(R.id.age_field);
        addressField = findViewById(R.id.address_field);
        userType = findViewById(R.id.user_type);
        registerButton = findViewById(R.id.register_user);

        String[] types = {"Applicant", "Recruiter"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types);
        userType.setAdapter(adapter);
        userType.setSelection(0);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = nameField.getText().toString();
                String address = addressField.getText().toString();
                int age = Integer.parseInt(ageField.getText().toString());
                String type = userType.getSelectedItem().toString();
                User user=new User(name,address,age,id,type);
                AppUtil.addToList(RegisterActivity.this, user);
                registerComplete(user);
            }
        });
    }

    private void checkAlredyRegistered()
    {
        List<User> users = AppUtil.getUsers(this);
        User current = null;
        for (User item : users) {
            if (item.getId().equals(id))
                current = item;
        }
        if (current != null) {
            registerComplete(current);
        }
    }

    private void registerComplete(User current)
    {
        Intent intent;

        if (current.getType().equals("Recruiter")){
            intent = new Intent(RegisterActivity.this, RecruiterHome.class);
        }else {
            intent = new Intent(RegisterActivity.this, ApplicantJobSearchActivity.class);
        }
        intent.putExtra(AppUtil.USER_OBJ, gson.toJson(current));
        startActivity(intent);
        finish();
    }


}
