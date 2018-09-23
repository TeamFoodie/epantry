/**
 * TODO: save new <User> signup in db, method to check login matches existing, method that blank fields make button disabled
 * test method for creating new login and making sure method returns match
 * test method for successful login to new activity?
 * test method for any blank fields that button is disabled
 */

package com.example.teamfoodie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.R;

import com.example.teamfoodie.database.UserTable;
import com.example.teamfoodie.models.User;

public class UserLoginActivity extends AppCompatActivity {

    EditText TFloginUsername;
    EditText TFloginPassword;
    private User currentUser;

    DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Log.i("UserLoginActivity", "Hello");
        this.currentUser = new User();

    }

    public void onClick(View v) {
        if(v.getId() == R.id.Bsignup) {
            Intent i = new Intent(UserLoginActivity.this, SignupFormActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.Blogin) {
            EditText a = (EditText) findViewById(R.id.TFloginUsername);
            String strUser = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFloginPassword);
            String strPass = b.getText().toString();
            UserTable userDB = new UserTable();

//            User currentUser = userDB.checkLogin(strUser, strPass, dbHandler);

            DatabaseHandler dbHandler = new DatabaseHandler(this);
            currentUser = userDB.checkLogin(strUser, strPass, dbHandler);
            System.out.println("current user is " + currentUser.toString());

            if(currentUser != null) {
                Intent i = new Intent(UserLoginActivity.this, LandingPageActivity.class);
                i.putExtra("USER_ID", currentUser.getUserID());
                System.out.println("USER ID FROM LOGIN IS " + currentUser.getUserID());
                startActivity(i);
            } else {
                Toast noMatch = Toast.makeText(UserLoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT);
                noMatch.show();
            }
        }
        if(v.getId() == R.id.BtoSignup) {
            Intent i = new Intent(UserLoginActivity.this, SignupFormActivity.class);
            startActivity(i);
        }
    }
}
