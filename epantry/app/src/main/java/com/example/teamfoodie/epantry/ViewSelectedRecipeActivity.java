package com.example.teamfoodie.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ViewSelectedRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView recipePhoto;
    private TextView recipeName;
    private TextView recipeCooking;
    private TextView recipeCalories;
    private TextView recipePeople;
    private LinearLayout ingredientsList;
    private LinearLayout proceduresList;
    private DatabaseHandler dbHandler;
    private Button makeRecipe;
    private int currentRECIPE_ID;
    private int currentNumberOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_recipe);


        this.recipePhoto = (ImageView) findViewById(R.id.recipe_photo);
        this.recipeName = (TextView) findViewById(R.id.recipe_name);
        this.recipeCooking = (TextView) findViewById(R.id.recipe_cooking_time);
        this.recipeCalories = (TextView) findViewById(R.id.recipe_calories);
        this.recipePeople = (TextView) findViewById(R.id.recipe_number_of_people);
        this.ingredientsList = (LinearLayout) findViewById(R.id.recipeIngredientsList);
        this.proceduresList = (LinearLayout) findViewById(R.id.recipeProcedureList);
        this.makeRecipe = (Button) findViewById(R.id.makeRecipeBtn);
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
        this.makeRecipe.setOnClickListener(this);
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

    //
    public void setAlertDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Set new number of people to cook for");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for OK button here
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for CANCEL button here, or leave in blank
            }
        });
        alert.show();
    }

    @Override
    public void onClick(View v) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Number of people to serve");
        dialog.setMessage("Recipe currently cooks for " + currentNumberOfPeople + " people. Do you want to change this?");
        dialog.setNegativeButton("No, Continue cooking",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ViewSelectedRecipeActivity.this, ChosenRecipeActivity.class);
                        intent.putExtra("currentRECIPE_ID", currentRECIPE_ID);
                        startActivity(intent);
                    }
                });
        dialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setAlertDialog();
                    }
                });

        dialog.show();

    }
}
