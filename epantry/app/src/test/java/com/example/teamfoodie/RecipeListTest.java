package com.example.teamfoodie;

import com.example.teamfoodie.epantry.ViewAllRecipesActivity;
import com.example.teamfoodie.models.Recipe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RecipeListTest {

    private ViewAllRecipesActivity viewAllRecipesActivity;

    @Before
    public void setUp(){
        this.viewAllRecipesActivity = new ViewAllRecipesActivity();
    }


    //Testing List selection by trialing arraylist ID
    @Test
    public void testingListSelection(){
        Recipe foundRecipe = new Recipe();
        foundRecipe = viewAllRecipesActivity.getCurrentRecipe(1);
        String recipename = "Anzac Biscuits";

        assertEquals(recipename, foundRecipe.getRecipeName());

    }




}
