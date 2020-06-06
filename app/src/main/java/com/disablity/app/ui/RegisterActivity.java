package com.disablity.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.disablity.app.util.AppUtil;
import com.disablity.app.R;
import com.disablity.app.data.User;

import java.util.List;

import static com.disablity.app.util.AppUtil.gson;

public class RegisterActivity extends AppCompatActivity
{

    private EditText nameField;
    private EditText ageField;
    private EditText addressField;
    private Button registerButton;
    private Spinner userType;
    private String id;
    private Spinner diablitySipnner;
    private TextView diablityLabel;


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
        diablitySipnner = findViewById(R.id.disablity_spiner);
        diablityLabel = findViewById(R.id.disablity_label);


        String[] types = {"Applicant", "Recruiter"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        userType.setAdapter(adapter);
        userType.setSelection(0);

        String[] disablityTypes = {"Blindness", "Intellectual Disability", "Hearing Impairment","Acid Attack Victim"};
        final ArrayAdapter<String> disablityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, disablityTypes);
        diablitySipnner.setAdapter(disablityAdapter);
        diablitySipnner.setSelection(0);


        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item = adapter.getItem(position);
                if (item.equals(types[0])) {
                    diablitySipnner.setVisibility(View.VISIBLE);
                    diablityLabel.setVisibility(View.VISIBLE);
                } else {
                    diablitySipnner.setVisibility(View.GONE);
                    diablityLabel.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        registerButton.setOnClickListener(v -> {
            String name = nameField.getText().toString();
            String address = addressField.getText().toString();
            int age = Integer.parseInt(ageField.getText().toString());
            String type = userType.getSelectedItem().toString();
            User user = new User(name, address, age, id, type);
            AppUtil.addToList(RegisterActivity.this, user);
            registerComplete(user);
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

        if (current.getType().equals("Recruiter")) {
            intent = new Intent(RegisterActivity.this, RecruiterHome.class);
        } else {
            intent = new Intent(RegisterActivity.this, ApplicantHome.class);
        }
        intent.putExtra(AppUtil.USER_OBJ, gson.toJson(current));
        startActivity(intent);
        finish();
    }


}
