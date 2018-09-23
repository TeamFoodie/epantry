package com.example.setavita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.setavita.epantry.database.DatabaseHandler;
import com.example.setavita.epantry.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

public class ViewPantryActivity extends AppCompatActivity {

    private int currentUSER_ID;
    private List<PantryIngredient> pantryList = new ArrayList<>();
    private DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pantry);
        this.dbHandler = new DatabaseHandler(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }

        System.out.println("Current user in View pantry is " + currentUSER_ID);


        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        ListView listView = (ListView) findViewById(R.id.viewAllPantry);
        listView.setAdapter(new CustomIngredientAdapter(this, pantryList, dbHandler));

        configureAddButton();

    }

    private void configureAddButton() {
        ImageView addButton = (ImageView) findViewById(R.id.addIngredient);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPantryActivity.this, PantryScannerActivity.class);
                intent.putExtra("USER_ID", currentUSER_ID);
                System.out.println("USER ID FROM VIEW PANTRY IS " + currentUSER_ID);
                startActivity(intent);
            }
        });

    }
}
