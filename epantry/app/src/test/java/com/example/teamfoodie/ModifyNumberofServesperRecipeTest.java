package com.example.teamfoodie;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.database.RecipeIngredientsTable;
import com.example.teamfoodie.epantry.AddIngredientActivity;
import com.example.teamfoodie.epantry.PantryUpdateActivity;
import com.example.teamfoodie.epantry.ViewSelectedRecipeActivity;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Procedure;
import com.example.teamfoodie.models.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModifyNumberofServesperRecipeTest {

    private List<Object> recipeIngredients;
    private List<Object> updatedRecipeIngredients;

    @Before
    public void setUp() {


        recipeIngredients = new ArrayList<>();
        recipeIngredients.add(new Ingredient("Softened butter", 250, "grams"));
        recipeIngredients.add(new Ingredient("Caster sugar", 0.75, "cup"));
        recipeIngredients.add(new Ingredient("Desiccated coconut", 1, "cup"));
        recipeIngredients.add(new Ingredient("vanilla essence", 1, "tsp"));
        recipeIngredients.add(new Ingredient("flour", 1.5, "cups"));
        recipeIngredients.add(new Ingredient("cocoa powder", 2, "tbsp"));
        recipeIngredients.add(new Ingredient("cornflakes", 1.5, "cups"));
        recipeIngredients.add(new Ingredient("cocoa powder", 1, "tsp"));
        recipeIngredients.add(new Ingredient("icing sugar", 2, "cups"));
        recipeIngredients.add(new Ingredient("softened butter", 1, "tbsp"));
        recipeIngredients.add(new Ingredient("boiling water", 3, "tbsp"));
        recipeIngredients.add(new Ingredient("vanilla essence", .25, "tsp"));
        recipeIngredients.add(new Ingredient("walnut halves", 13, " "));

    }

    @Test
    public void testDecreaseOfServes() {
        updatedRecipeIngredients = RecipeIngredientsTable.calculateNewMeasurements(recipeIngredients, 4, 1);
        Ingredient firstIngredient = (Ingredient)updatedRecipeIngredients.get(0);
        String decreasedQuantity = String.valueOf(firstIngredient.getMeasurement());

        //Initial value to serve 4 people is 250grams of softened butter (first ingredient)
        //so if 250 serves 4 people then should only take 62.5 grams to make enough fro 1 person
        //hence quantity of softened butter should be 62.5.
        String actualValue =  String.valueOf(62.5);


        assertEquals(actualValue, decreasedQuantity);
    }

    @Test
    public void testIncreaseOfServes() {
        updatedRecipeIngredients = RecipeIngredientsTable.calculateNewMeasurements(recipeIngredients, 4, 6);
        Ingredient firstIngredient = (Ingredient)updatedRecipeIngredients.get(0);
        double increasedQuantity = firstIngredient.getMeasurement();

        //Initial value to serve 4 people is 250grams of softened butter (first ingredient)
        //so if 250 serves 4 people then should take 375 grams to make enough for 6 people
        double actualValue = 375.0;


        assertEquals(actualValue, increasedQuantity, 0);
    }

    /**
     * Ensure that array sizes are the same and that there is no data loss when ingredient
     * array is passed through for recalculation
     */
    @Test
    public void testIntegrityOfUpdatedArray() {
        updatedRecipeIngredients = RecipeIngredientsTable.calculateNewMeasurements(recipeIngredients, 4, 6);
        int returnedQuantity = updatedRecipeIngredients.size();
        int actualQuantity = 13;

        assertEquals(actualQuantity, returnedQuantity);
    }

}
