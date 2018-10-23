package com.example.teamfoodie;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.AddIngredientActivity;
import com.example.teamfoodie.epantry.PantryUpdateActivity;
import com.example.teamfoodie.epantry.ViewSelectedRecipeActivity;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModifyNumberofServesperRecipe {

    private Recipe recipe;
    private Ingredient ingredient;
    private DatabaseHandler dbHandler;
    private ViewSelectedRecipeActivity selectedRecipe;

    @Before
    public void setUp() {
        selectedRecipe = new ViewSelectedRecipeActivity();
        dbHandler = new DatabaseHandler(selectedRecipe);

    }


    @After
    public void tearDown() {
    }

    /**
     * Once ingredient is created it is added to database using the add handle method in database handler. If insert is successful, boolean value of true is returned by Add Handle method.
     */
    @Test
    public void testDecreaseOfServes() {
        recipe = (Recipe) dbHandler.findHandle("1", "Recipe");



        boolean valueInserted = dbHandler.addHandle(ingredient);
        assertTrue(valueInserted);
    }



}
