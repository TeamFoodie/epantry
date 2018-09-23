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
    private ImageView uploadRecipe;
    private ImageView shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        restock = (ImageView) findViewById(R.id.myPantry);
        restock.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.find_recipe);
        search.setOnClickListener(this);
        uploadRecipe = (ImageView) findViewById(R.id.newRecipe);
        uploadRecipe.setOnClickListener(this);
        shoppingList = (ImageView) findViewById(R.id.shop_list);
        shoppingList.setOnClickListener(this);

    }

    //    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.myPantry) {
            Intent intent = new Intent(LandingPageActivity.this, ViewPantryActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.find_recipe){
            Intent intent = new Intent(LandingPageActivity.this, ViewAllRecipesActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.newRecipe){
            Intent intent = new Intent(LandingPageActivity.this, MakeFoodActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.shop_list){
            Intent intent = new Intent(LandingPageActivity.this, FoodMaterialListActivity.class);
            startActivity(intent);
        }
    }
}