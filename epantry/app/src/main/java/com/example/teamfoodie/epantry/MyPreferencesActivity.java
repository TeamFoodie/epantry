//for String : arrayList { new Checkbox(); }

package com.example.teamfoodie.epantry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MyPreferencesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences);

        System.out.println("Loaded class");
    }
}
