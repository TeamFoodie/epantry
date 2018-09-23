package com.example.setavita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView restock;
    private ImageView search;
    private int currentUSER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        restock = (ImageView) findViewById(R.id.myPantry);
        restock.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.searchRecipe);
        search.setOnClickListener(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
//                currentUSER_ID = 1;
                System.out.println("Bundle extra was NULL user");
            }else{
                currentUSER_ID = extras.getInt("USER_ID");
            }
        }else{
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }
    }

//    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.myPantry) {
            Intent intent = new Intent(LandingPageActivity.this, ViewPantryActivity.class);
            intent.putExtra("USER_ID", currentUSER_ID);
            startActivity(intent);
        }else if (v.getId() == R.id.searchRecipe){
            Intent intent = new Intent(LandingPageActivity.this, ViewAllRecipesActivity.class);
//            intent.putExtra("USER_ID", currentUSER_ID);
            startActivity(intent);
        }
    }
}
