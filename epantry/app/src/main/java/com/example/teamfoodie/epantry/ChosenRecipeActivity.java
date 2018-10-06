package com.example.teamfoodie.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.Recipe;

import java.util.List;

public class ChosenRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView recipePhoto;
    private TextView recipeName;
    private TextView recipeCooking;
    private TextView recipeCalories;
    private TextView recipePeople;
    private LinearLayout ingredientsList;
    private LinearLayout proceduresList;
    private DatabaseHandler dbHandler;
    private ImageView chosen_backButton;
    private int currentRECIPE_ID;
    private int currentNumberOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosen_recipe);


        this.recipePhoto = (ImageView) findViewById(R.id.chosen_recipe_photo);
        this.recipeName = (TextView) findViewById(R.id.chosen_recipe_name);
        this.recipeCooking = (TextView) findViewById(R.id.chosen_recipe_cooking_time);
        this.recipeCalories = (TextView) findViewById(R.id.chosen_recipe_calories);
        this.recipePeople = (TextView) findViewById(R.id.chosen_recipe_number_of_people);
        this.ingredientsList = (LinearLayout) findViewById(R.id.chosen_recipeIngredientsList);
        this.proceduresList = (LinearLayout) findViewById(R.id.chosen_recipeProcedureList);
        this.chosen_backButton = (ImageView) findViewById(R.id.chosen_backButton);
        this.dbHandler = new DatabaseHandler(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL user");
            } else {
                currentRECIPE_ID = extras.getInt("RECIPE_ID");
            }
        } else {
            currentRECIPE_ID = (Integer) savedInstanceState.getSerializable("RECIPE_ID");
            System.out.println("savedInstance was NULL");
        }

        Object obj = dbHandler.findHandle(String.valueOf(currentRECIPE_ID), "StoredRecipe");
        Recipe recipe = (Recipe) obj;

        setInformation(recipe);
        compileRecipeDetails();
        this.chosen_backButton.setOnClickListener(this);
    }

    public void setInformation(Recipe recipe) {
        this.recipePhoto.setImageResource(recipe.getPhoto());
        this.recipeName.setText("Name: " + recipe.getRecipeName());
        this.recipeCooking.setText("Cooking Time: " + recipe.getCookingTime() + " minutes");
        this.recipeCalories.setText("Calorie Count: " + recipe.getCalorieCount());
        this.recipePeople.setText("Serves: " + recipe.getNumberOfPeople() + " people");
        currentNumberOfPeople = recipe.getNumberOfPeople();


    }

    public void compileRecipeDetails() {
        List<Object> objectList;

        objectList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 1);
        CustomProcedureAdapter adapter = new CustomProcedureAdapter(this, objectList, 1);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, ingredientsList);
            ingredientsList.addView(view);
        }

        objectList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 2);
        adapter.setListType(2, objectList);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, proceduresList);
            proceduresList.addView(view);
        }
    }


    public void onClick(View v) {
        Intent intent = new Intent(ChosenRecipeActivity.this, LandingPageActivity.class);
        startActivity(intent);
    }
}
