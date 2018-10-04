package com.example.teamfoodie.epantry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.Procedure;
import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ViewSelectedRecipeActivity extends AppCompatActivity {

    private ImageView recipePhoto;
    private TextView recipeName;
    private TextView recipeCooking;
    private TextView recipeCalories;
    private TextView recipePeople;
    private LinearLayout ingredientsList;
    private LinearLayout proceduresList;
    private DatabaseHandler dbHandler;
    private int currentRECIPE_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_recipe);


        this.recipePhoto = (ImageView) findViewById(R.id.recipe_photo);
        this.recipeName = (TextView) findViewById(R.id.recipe_name);
        this.recipeCooking = (TextView) findViewById(R.id.recipe_cooking_time);
        this.recipeCalories = (TextView) findViewById(R.id.recipe_calories);
        this.ingredientsList = (LinearLayout) findViewById(R.id.recipeIngredientsList);
        this.proceduresList = (LinearLayout) findViewById(R.id.recipeProcedureList);
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
    }

    public void setInformation(Recipe recipe){
        this.recipePhoto.setImageResource(recipe.getPhoto());
        this.recipeName.setText("Name: " + recipe.getRecipeName());
        this.recipeCooking.setText("Cooking Time: " + recipe.getCookingTime());
        this.recipeCalories.setText("Calorie Count: " + recipe.getCalorieCount());


    }

    public void compileRecipeDetails(){
        List<Object> objectList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();
        List<String> procedureList = new ArrayList<>();

        objectList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 1);
        for(int i = 0; i < objectList.size(); i++){
            Ingredient ingredient = (Ingredient) objectList.get(i);
            ingredientList.add(ingredient);
        }
        objectList = dbHandler.loadAllRecipeDetails(currentRECIPE_ID, 2);
        for(int i = 0; i < objectList.size(); i++){
            Procedure procedure = (Procedure) objectList.get(i);
            procedureList.add(procedure.getStep());
        }

        CustomRecipeAdapter adapter = new CustomRecipeAdapter(this, ingredientList);
        for(int i = 0; i < adapter.getCount(); i++){
            View view = adapter.getView(i, null, ingredientsList);
            ingredientsList.addView(view);
        }
        ArrayAdapter<String> procedureAdapter = new ArrayAdapter<String>(this, R.layout.procedure_list);
        for(int i = 0; i < procedureAdapter.getCount(); i++){
            View view = procedureAdapter.getView(i, null, proceduresList);
            proceduresList.addView(view);
        }


    }
}
