package com.example.setavita.epantry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.setavita.epantry.R;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView restock;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        restock = (ImageView) findViewById(R.id.myPantry);
        restock.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.searchRecipe);
        search.setOnClickListener(this);

    }

//    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.myPantry) {
            Intent intent = new Intent(LandingPageActivity.this, ViewPantryActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.searchRecipe){
            Intent intent = new Intent(LandingPageActivity.this, ViewAllRecipesActivity.class);
            startActivity(intent);
        }
    }
}
