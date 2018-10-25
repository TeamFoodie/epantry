package com.example.teamfoodie.epantry;

import android.app.NotificationManager;
import android.content.Context;
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
import com.example.teamfoodie.database.RecipeIngredientsTable;
import com.example.teamfoodie.epantry.listAdapters.CustomProcedureAdapter;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ViewSelectedRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView recipePhoto;
    private TextView recipeName;
    private TextView recipeCooking;
    private TextView recipeCalories;
    private TextView recipePeople;
    private LinearLayout ingredientsListView;
    private LinearLayout proceduresListView;
    private DatabaseHandler dbHandler;
    private Button makeRecipe;
    private Button changeServes;
    private Button close;
    private List<Object> ingredientList;
    private List<Object> procedureList;
    private int currentRECIPE_ID;
    private int currentUSER_ID;
    private int currentNumberOfPeople;
    private boolean servesChanged;
    private List<Object> UPDATED_INGREDIENTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_recipe);


        Bundle extras = getIntent().getExtras();
        currentRECIPE_ID = extras.getInt("RECIPE_ID");
        currentUSER_ID = extras.getInt("USER_ID");

        this.recipePhoto = (ImageView) findViewById(R.id.recipe_photo);
        this.recipeName = (TextView) findViewById(R.id.recipe_name);
        this.recipeCooking = (TextView) findViewById(R.id.recipe_cooking_time);
        this.recipeCalories = (TextView) findViewById(R.id.recipe_calories);
        this.recipePeople = (TextView) findViewById(R.id.recipe_number_of_people);
        this.ingredientsListView = (LinearLayout) findViewById(R.id.recipeIngredientsList);
        this.proceduresListView = (LinearLayout) findViewById(R.id.recipeProcedureList);
        this.makeRecipe = (Button) findViewById(R.id.makeRecipeBtn);
        this.changeServes = (Button) findViewById(R.id.btnChangeServes);
        this.close = (Button) findViewById(R.id.close_btn);
        this.close.setVisibility(View.INVISIBLE);
        this.dbHandler = new DatabaseHandler(this);

        this.close.setOnClickListener(this);
        this.changeServes.setOnClickListener(this);
        this.makeRecipe.setOnClickListener(this);

        Object obj = dbHandler.findHandle(String.valueOf(currentRECIPE_ID), "StoredRecipe");
        Recipe recipe = (Recipe) obj;
        setInformation(recipe);

        this.ingredientList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 1);
        this.procedureList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 2);
        compileRecipeDetails(ingredientList, procedureList);

    }

    public void setInformation(Recipe recipe) {
        this.recipePhoto.setImageResource(Integer.valueOf(recipe.getPhoto()));
        this.recipeName.setText("Name: " + recipe.getRecipeName());
        this.recipeCooking.setText("Cooking Time: " + recipe.getCookingTime() + " minutes");
        this.recipeCalories.setText("Calorie Count: " + recipe.getCalorieCount());
        this.recipePeople.setText("Serves: " + recipe.getNumberOfPeople() + " people");
        currentNumberOfPeople = recipe.getNumberOfPeople();


    }

    public void compileRecipeDetails(List<Object> ingredients, List<Object> procedures) {
        CustomProcedureAdapter adapter = new CustomProcedureAdapter(this, ingredients, 1);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, ingredientsListView);
            ingredientsListView.addView(view);
        }

        adapter.setListType(2, procedures);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, proceduresListView);
            proceduresListView.addView(view);
        }
    }


    public void setAlertDialog(String message) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(message);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int newNumberOfPeople = 0;
                try {
                    newNumberOfPeople = Integer.parseInt(input.getText().toString());
                } catch (NumberFormatException numberExecption) {
                    setAlertDialog("Please enter in numbers ONLY");
                }

                if (newNumberOfPeople != 0) {
                    UPDATED_INGREDIENTS = RecipeIngredientsTable.calculateNewMeasurements(ingredientList, currentNumberOfPeople, newNumberOfPeople);
                    compileRecipeDetails(UPDATED_INGREDIENTS, procedureList);
                    servesChanged = true;

                }
            }
        });
        alert.setNegativeButton("Cancel", null);
        alert.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnChangeServes) {

            final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
            dialog.setTitle("Number of people to serve");
            dialog.setMessage("Recipe currently cooks for " + currentNumberOfPeople + " people. Do you want to change this?");
            dialog.setNegativeButton("No", null);
            dialog.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setAlertDialog("Set new number of people to cook for");
                        }
                    });

            dialog.show();

        } else if (v.getId() == R.id.makeRecipeBtn) {

            dbHandler.setUSER_ID(currentUSER_ID);
            List<PantryIngredient> pantryIngredients = dbHandler.loadAllPantryIngredients(currentUSER_ID);

//            if (servesChanged) {
//                compareIngredientsPantryVSRecipe(UPDATED_INGREDIENTS, pantryIngredients);
//            } else {
//                compareIngredientsPantryVSRecipe(ingredientList, pantryIngredients);
//            }

            this.makeRecipe.setVisibility(View.INVISIBLE);
            this.close.setVisibility(View.VISIBLE);
            this.changeServes.setVisibility(View.INVISIBLE);


        } else if (v.getId() == R.id.close_btn){
            Intent intent = new Intent(ViewSelectedRecipeActivity.this, LandingPageActivity.class);
            intent.putExtra("RECIPE_ID", currentRECIPE_ID);
            intent.putExtra("USER_ID", currentUSER_ID);
            startActivity(intent);
        }


    }

    public void recipemade(){
        this.makeRecipe.setVisibility(View.INVISIBLE);
        this.close.setVisibility(View.VISIBLE);
        this.changeServes.setVisibility(View.INVISIBLE);
    }


    public void compareIngredientsPantryVSRecipe(List<Object> recipeIngredientsList, List<PantryIngredient> pantryList) {

        List<Integer> thresholds = (List<Integer>) dbHandler.findHandle(String.valueOf(currentUSER_ID), "Thresholds");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationClass notify = new NotificationClass();

        for (int RECIPE_INDEX = 0; RECIPE_INDEX < recipeIngredientsList.size(); RECIPE_INDEX++) {
            Ingredient ingredient = (Ingredient) recipeIngredientsList.get(RECIPE_INDEX);
            for (int PANTRY_INDEX = 0; PANTRY_INDEX < pantryList.size(); PANTRY_INDEX++) {
                System.out.println("Nested Loop");
                PantryIngredient pantryIngredient = pantryList.get(PANTRY_INDEX);
                if (ingredient.getName().toLowerCase().contains(pantryIngredient.getIngredientName().toLowerCase())) {
                    System.out.println("Here if");
                    double newQuanity = pantryIngredient.getCurrentQuantity() - ingredient.getMeasurement();
                    pantryIngredient.setCurrentQuantity((newQuanity));
                    System.out.println(newQuanity + " for ingredient " + pantryIngredient.getIngredientName());
                    System.out.println(dbHandler.subtractQuantity(pantryIngredient));

                    notify.calculateThreshold(this, notificationManager, currentUSER_ID, pantryIngredient, thresholds);
                }else{
                    final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
                    dialog.setTitle("Missing Ingredients");
                    dialog.setMessage("Some of the recipe ingredients do not exist in your pantry, do you want to continue and add the missing ingredients to your shopping list?");
                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    dialog.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    recipemade();
                                }
                            });

                    dialog.show();

                }
            }


        }

    }



}