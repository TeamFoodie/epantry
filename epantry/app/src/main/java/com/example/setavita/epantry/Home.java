package com.example.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView tvUsername;
    TextView tvPassword;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUsername = (TextView) findViewById(R.id.usernameInput); //create a TextView as matching type in xml
        username = getIntent().getStringExtra("Username");
        tvUsername.setText(username);
//      tvUsername.setText(getIntent().getStringExtra("Username"));

        tvPassword = (TextView) findViewById(R.id.passwordInput); //create a TextView as matching type in xml
        password = getIntent().getStringExtra("Password");
        tvPassword.setText(password);

    }
}
