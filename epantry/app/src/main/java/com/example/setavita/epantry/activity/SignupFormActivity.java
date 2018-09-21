/**
 * TODO:
 * test method to make sure it creates new user with login if no match - assertNotNull
 * test method that username field isn't empty
 * test method that it checks password matches confirmed password
 */
package com.example.setavita.epantry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.setavita.epantry.database.DatabaseHandler;
import com.example.setavita.epantry.R;
import com.example.setavita.epantry.models.User;

public class SignupFormActivity extends AppCompatActivity {

    EditText ETusername;
    EditText ETemail;
    EditText ETpassword;
    EditText ETconfirmpassword;
    Button Bcreateaccount;
    DatabaseHandler db = new DatabaseHandler(this);
    SignupFormActivity seterror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Log.i("SignupFormActivity", "Hello");
        Log.i("SignupFormActivity", "Test");

        ETusername = (EditText) findViewById(R.id.TFname);
        ETemail = (EditText) findViewById(R.id.TFemail);
        ETpassword = (EditText) findViewById(R.id.TFpassword);
        ETconfirmpassword = (EditText) findViewById(R.id.TFconfirmpassword);

        Bcreateaccount = (Button) findViewById(R.id.Bsignup);
        Bcreateaccount.setEnabled(false);

        ETpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordInput = ETpassword.getText().toString();
                String confirmpasswordInput = ETconfirmpassword.getText().toString();

                checkPasswords(passwordInput,confirmpasswordInput);
                if (s.toString().equals("")) {
                    Bcreateaccount.setEnabled(false);
                } else {
                    Bcreateaccount.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = ETemail.getText().toString();

                checkEmail(emailInput);
                if (s.toString().equals("")) {
                    Bcreateaccount.setEnabled(false);
                } else {
                    Bcreateaccount.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.Bsignup) {
            String username = ETusername.getText().toString();
            String email = ETemail.getText().toString();
            String password = ETpassword.getText().toString();
            String confirmpassword = ETconfirmpassword.getText().toString();

            if (ETusername.getError() == null && ETemail.getError() == null &&
                    ETpassword.getError() == null && ETconfirmpassword.getError() == null
                    && db.checkExistingUser(username) == false) {
                User user = new User(username, password, email);

                db.addHandle(user);


                Log.i("SignupFormActivity", "Confirmed login");
                Intent i = new Intent(SignupFormActivity.this, LandingPageActivity.class);
                startActivity(i);
            }
        }
        if (v.getId() == R.id.BtoLogin) {
            Intent i = new Intent(SignupFormActivity.this, UserLoginActivity.class);
            startActivity(i);
        }
    }

    public boolean checkEmail(String email) {
        if (!email.contains("@") || !email.contains(".com")) {
            ETemail.setError("Email must be valid!");
            return false;
        } else {
            ETemail.setError(null);
            return true;
        }
    }

    public boolean checkPasswords(String password, String confirmpassword) {
        if (!password.equals(confirmpassword)) {
            setError(ETconfirmpassword,"Passwords don't match!");
            return false;
        } else if (!(password.length() >= 6)) {
            ETpassword.setError("Password must be at least 6 characters");
            return false;
        } else {
            ETpassword.setError(null);
            ETconfirmpassword.setError(null);
            return true;
        }
    }

    public void setError(EditText ET, String message) {
        ET.setError(message);
    }
}
