//TODO:
//1) get Title for each ingredient in pantry
//          - for String : arrayList { new Checkbox(); }
//2) checkboxes:
//      * if ticked:
//              - calculateThreshold()
//              - set each ingredient's threshold to "threshold"
//              - boolean priority = true
//      * if priority == true
//              - ... send alert to phone every "alertDays"
//3) add when back button clicked...

package com.example.teamfoodie.epantry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MyPreferencesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Preferences"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        System.out.println("Loaded class");
    }
}
