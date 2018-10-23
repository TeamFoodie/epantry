package com.example.teamfoodie;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.ViewAllRecipesActivity;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.Procedure;
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
        String recipename = "Afghans";

        assertEquals(recipename, foundRecipe.getRecipeName());

    }

//    Testing List that new Recipe is added
    @Test
    public void testAddRecipe(){

        Ingredient ing1 = new Ingredient("Softened butter", 250, "grams");
        Ingredient ing2 = new Ingredient("Caster sugar", 0.75, "cup");
        Ingredient ing3 = new Ingredient("Desiccated coconut", 1, "cup");
        Ingredient ing4 = new Ingredient("vanilla essence", 1, "tsp");
        Ingredient ing5 = new Ingredient("flour", 1.5, "cups");
        Ingredient ing6 = new Ingredient("cocoa powder", 2, "tbsp");
        Ingredient ing7 = new Ingredient("cornflakes", 1.5, "cups");
        Ingredient ing8 = new Ingredient("cocoa powder", 1, "tsp");
        Ingredient ing9 = new Ingredient("icing sugar", 2, "cups");
        Ingredient ing10 = new Ingredient("softened butter", 1, "tbsp");
        Ingredient ing11 = new Ingredient("boiling water", 3, "tbsp");
        Ingredient ing12 = new Ingredient("vanilla essence", .25, "tsp");
        Ingredient ing13 = new Ingredient("walnut halves", 13, " ");

        ArrayList<Ingredient> ingredientarray = new ArrayList<>();
        ingredientarray.add(ing1);
        ingredientarray.add(ing2);
        ingredientarray.add(ing3);
        ingredientarray.add(ing4);
        ingredientarray.add(ing5);
        ingredientarray.add(ing6);
        ingredientarray.add(ing7);
        ingredientarray.add(ing8);
        ingredientarray.add(ing9);
        ingredientarray.add(ing10);
        ingredientarray.add(ing11);
        ingredientarray.add(ing12);
        ingredientarray.add(ing13);

        ArrayList<Procedure> procedureArray = new ArrayList<>();
        Procedure procedure1 = new Procedure("1. Preheat the oven to 180C.");
        Procedure procedure2 = new Procedure("2. Cream the butter and sugar together. Add the coconut and vanilla essence.");
        Procedure procedure3 = new Procedure("3. Sift the four and cocoa and add to the bowl. Mix well and add the cornflakes. Lightly blend.");
        Procedure procedure4 = new Procedure("4. Place spoonfuls of the mixture on a lightly greased baking tray, allowing room for them to spread.");
        Procedure procedure5 = new Procedure("5. Bake for 20 minutes.");
        Procedure procedure6 = new Procedure("6. Allow to cool then spread with chocolate icing.");
        Procedure procedure7 = new Procedure("7. For the icing, sift sugar and cocoa into a bowl. Add butter, then water and vanilla. Beat to a spreadable consistency. Once iced, top biscuits with a walnut half.");

        procedureArray.add(procedure1);
        procedureArray.add(procedure2);
        procedureArray.add(procedure3);
        procedureArray.add(procedure4);
        procedureArray.add(procedure5);
        procedureArray.add(procedure6);
        procedureArray.add(procedure7);

        Recipe r1 = new Recipe("Afghans", R.drawable.afghans, "Afghans are a kiwi classic", 650,40,1,4,"Comfort Food", ingredientarray, procedureArray);


        boolean inserted = dbHandler.addHandle(r1);

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
