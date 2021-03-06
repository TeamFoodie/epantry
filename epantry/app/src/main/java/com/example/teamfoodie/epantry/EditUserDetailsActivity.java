package com.example.teamfoodie.epantry;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.database.UserTable;
import com.example.teamfoodie.models.User;

public class EditUserDetailsActivity extends AppCompatActivity {

    int userID;
    EditText ETnewUsername;
    EditText ETnewEmail;
    EditText ETnewPassword;
    EditText ETconfirmNewPassword;
    Button BsaveChanges;
    User user = new User();
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Edit Account Details"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        userID = getIntent().getExtras().getInt("USER_ID");

        User existingUser = (User) db.findHandle(String.valueOf(userID), "ChangingUser");
        ETnewUsername = (EditText) findViewById(R.id.TFnewName);
        ETnewUsername.setText(existingUser.getUsername());
        ETnewEmail = (EditText) findViewById(R.id.TFnewEmail);
        ETnewEmail.setText(existingUser.getEmail());
        ETnewPassword = (EditText) findViewById(R.id.TFnewPassword);
        ETnewPassword.setText(existingUser.getPassword());
        ETconfirmNewPassword = (EditText) findViewById(R.id.TFconfirmNewPassword);

        BsaveChanges = (Button) findViewById(R.id.Bsave);
        BsaveChanges.setEnabled(false);

        ETnewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordInput = ETnewPassword.getText().toString();
                String confirmpasswordInput = ETconfirmNewPassword.getText().toString();

                checkPasswords(passwordInput, confirmpasswordInput);
                if (s.toString().equals("")) {
                    BsaveChanges.setEnabled(false);
                } else {
                    BsaveChanges.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ETnewEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = ETnewEmail.getText().toString();

                checkEmail(emailInput);
                if (s.toString().equals("")) {
                    BsaveChanges.setEnabled(false);
                } else {
                    BsaveChanges.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Handles user interaction with GUI to login
     */
    public void onClick(View v) {
        if (v.getId() == R.id.Bsave) {
            Log.i("Edit details: ", "CLICKED");
            String username = ETnewUsername.getText().toString();
            String email = ETnewEmail.getText().toString();
            String password = ETnewPassword.getText().toString();
            String confirmpassword = ETconfirmNewPassword.getText().toString();
            UserTable userDB = new UserTable();

            if (db.checkExistingUser(username)) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Username is taken, please try again",
                        Toast.LENGTH_SHORT);
            }

            if (ETnewUsername.getError() == null && ETnewEmail.getError() == null &&
                    ETnewPassword.getError() == null && ETconfirmNewPassword.getError() == null
                    && !db.checkExistingUser(username) && checkPasswords(password, confirmpassword)) {

                DatabaseHandler dbHandler = new DatabaseHandler(this);
                user = dbHandler.getUser(userID);


                //sets currentUser to new values
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);


                ContentValues values = new ContentValues();
                values.put("UserName", user.getUsername());
                values.put("Password", user.getPassword());
                values.put("Email", user.getEmail());

                db.updateUserDetails(values, userID);


                Toast toast = Toast.makeText(getApplicationContext(), "Changes made successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(EditUserDetailsActivity.this, LandingPageActivity.class);
                intent.putExtra("USER_ID", userID);
                intent.putExtra("UserName", user.getUsername());
                intent.putExtra("Email", user.getEmail());
                intent.putExtra("Password", user.getPassword());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean checkEmail(String email) {
        if (!email.contains("@") || !email.contains(".com")) {
            ETnewEmail.setError("Email must be valid!");
            return false;
        } else {
            ETnewEmail.setError(null);
            return true;
        }
    }

    public boolean checkPasswords(String password, String confirmpassword) {
        if (!password.equals(confirmpassword)) {
            setError(ETconfirmNewPassword, "Passwords don't match!");
            return false;
        } else if (!(password.length() >= 6)) {
            setError(ETnewPassword, "Password must be at least 6 characters");
            return false;
        } else {
            ETnewPassword.setError(null);
            ETconfirmNewPassword.setError(null);
            return true;
        }
    }

    public void setError(EditText ET, String message) {
        ET.setError(message);
    }
}