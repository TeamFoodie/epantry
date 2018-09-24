package com.example.teamfoodie;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.ViewAllRecipesActivity;
import com.example.teamfoodie.models.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RecipeListTest {

    private ViewAllRecipesActivity viewAllRecipesActivity;
    private DatabaseHandler dbHandler;

    @Before
    public void setUp(){
        this.viewAllRecipesActivity = new ViewAllRecipesActivity();
        this.dbHandler = new DatabaseHandler(viewAllRecipesActivity);
    }


//    Testing List selection by trialing arraylist ID
    @Test
    public void testRecipeRetrival(){
        Object obj = new Object();
        obj = dbHandler.findHandle("1", "StoredRecipe");
        Recipe foundRecipe = (Recipe) obj;
        String recipename = "Anzac Biscuits";

        assertEquals(recipename, foundRecipe.getRecipeName());

    }

//    Testing List that new Recipe is added
    @Test
    public void testAddRecipe(){
        Recipe recipe = new Recipe("New Recipe", 1, "This is a mock recipe", "www.mock.com");
        boolean inserted = dbHandler.addHandle(recipe);

        assertTrue(inserted);
    }

//    Testing that all recipes have been added
//    Currently 17 items 24/09/2018
    @Test
    public void testRetrivalOfAllRecipes(){
        List<Recipe> foundList = new ArrayList<>();
        foundList = dbHandler.loadAllRecipes();

        assertEquals(17, foundList.size());
    }




}
