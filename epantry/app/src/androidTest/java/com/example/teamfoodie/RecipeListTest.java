package com.example.teamfoodie;

import android.view.View;

import com.example.teamfoodie.epantry.ViewAllRecipesActivity;

public class RecipeListTest {

    public ViewAllRecipesActivity recipesActivity;

    public void setUp(){
        this.recipesActivity = new ViewAllRecipesActivity();
        View recipeListView = recipesActivity.onCreate();
    }


}
