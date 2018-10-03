package com.example.teamfoodie.epantry;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;


import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

    public class DietaryRequirementsActivity extends AppCompatActivity {

        TextView allergiesText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dietary_requirements);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setTitle("Dietary Requirements"); // for set actionbar title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
            System.out.println("Loaded dietary req class");

            allergiesText = (TextView)findViewById(R.id.allergiesTV);
            allergiesText.setPaintFlags(allergiesText.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            //

    }
}