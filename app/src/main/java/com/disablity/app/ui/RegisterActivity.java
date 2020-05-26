package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.disablity.app.R;
import com.disablity.app.data.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity
{

    EditText nameField;
    EditText ageField;
    EditText addressField;
    Button registerButton;
    Spinner userType;
    final String userList = "user.list";


    private List<User> getUsers()
    {
        SharedPreferences pref = getSharedPreferences("users", Context.MODE_PRIVATE);
        ArrayList<User> userList = new ArrayList<>();
        if (pref.contains(this.userList)) {
            String listData = pref.getString(this.userList, "");
            Gson gson = new Gson();
            User[] users = gson.fromJson(listData, User[].class);
            userList.addAll(Arrays.asList(users));
    }
        return userList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameField=findViewById(R.id.name_field);
        ageField=findViewById(R.id.age_field);
        addressField=findViewById(R.id.address_field);
        userType=findViewById(R.id.user_type);
        registerButton=findViewById(R.id.register_user);

        String[] types={"Applicant","Recruiter"};
        ArrayAdapter<String>  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
        userType.setAdapter(adapter);
//        useT


        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String  name = nameField.getText().toString();
                String  address=addressField.getText().toString();
                int age =Integer.parseInt(ageField.getText().toString());
                String type=userType.getSelectedItem().toString();
//                User user=User()



            }
        });




    }
}
