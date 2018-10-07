package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.database.UserTable;
import com.example.teamfoodie.models.User;

/*
 * The UserLoginActivity class stores information about a
 * user's name and password and accesses the
 * Database to authenticate an existing user.
 */
public class UserLoginActivity extends AppCompatActivity {

    EditText TFloginUsername;
    EditText TFloginPassword;
    private User currentUser;

    DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        this.currentUser = new User();

    }

    /**
     * Handles user interaction with GUI to login
     */
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


            DatabaseHandler dbHandler = new DatabaseHandler(this);
            currentUser = userDB.checkLogin(strUser, strPass, dbHandler);

            if(currentUser != null) {
                Intent i = new Intent(UserLoginActivity.this, LandingPageActivity.class);
                i.putExtra("USER_ID", currentUser.getUserID());
                i.putExtra("Username", strUser);
                i.putExtra("Email", dbHandler.getUserEmail(strUser)); //pass values to other activity
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
