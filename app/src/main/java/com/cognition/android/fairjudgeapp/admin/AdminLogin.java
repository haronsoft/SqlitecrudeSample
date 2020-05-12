package com.cognition.android.fairjudgeapp.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cognition.android.fairjudgeapp.R;

public class AdminLogin extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView error_here;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        /*Set title for actionBar*/
        this.setTitle("Admin Login");

        /*Initialization of views*/
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        error_here = findViewById(R.id.error);
        /*Listen to button click event*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString().trim();
                String pass_word = password.getText().toString().trim();
                //validate username and password
                if (user_name.isEmpty()) {
                    username.setTextColor(Color.RED);
                    error_here.setText("Username cannot be empty");
                } else if (pass_word.isEmpty()) {
                    password.setHintTextColor(Color.RED);
                    error_here.setText("Password cannot be empty");
                } else {
                    loginAdmin();
                }
            }
        });

    }

    private void loginAdmin() {
        String user_name = username.getText().toString().trim();
        String pass_word = password.getText().toString().trim();
        //User Validation
        if (user_name.equals("revenue") && pass_word.equals("admin123")) {
            //launch admin page
            startActivity(new Intent(AdminLogin.this, AdminLog.class));
        } else {
            Toast.makeText(this, "username doesnot match our Records!!", Toast.LENGTH_SHORT).show();
        }
    }
}
