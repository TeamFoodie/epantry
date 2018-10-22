package com.example.teamfoodie;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.AddIngredientActivity;
import com.example.teamfoodie.epantry.PantryUpdateActivity;
import com.example.teamfoodie.models.PantryIngredient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdatePantryIngredients {

    private PantryUpdateActivity update;
    private AddIngredientActivity addIngredient;
    private DatabaseHandler dbHandler;
    private PantryIngredient ingredient = new PantryIngredient("12345", "Ingredient1", 23, 23, "grams", 111);

    @Before
    public void setUp() {
        update = new PantryUpdateActivity();
        addIngredient = new AddIngredientActivity();

    }


    @After
    public void tearDown() {
    }

    /**
     * Once ingredient is created it is added to database using the add handle method in database handler. If insert is successful, boolean value of true is returned by Add Handle method.
     */
    @Test
    public void testUpdateofDatabase() {
        dbHandler = new DatabaseHandler(addIngredient);
        boolean valueInserted = dbHandler.addHandle(ingredient);
        assertTrue(valueInserted);
    }

    /**
     * Ingredient is inserted into database and then searches database to return the ingredient - using the ID;
     * If ingredient returned is the same as ingredient inserted - then test is true.
     */
    @Test
    public void searchDatabaseForIngredient() {
        dbHandler = new DatabaseHandler(addIngredient);
        boolean valueInserted = dbHandler.addHandle(ingredient);
        PantryIngredient foundIngredient = new PantryIngredient();
        if(valueInserted){
            Object obj = dbHandler.findHandle(ingredient.getIngredientID(), "PantryIngredient");
            foundIngredient = (PantryIngredient) obj;
        }

        assertEquals(ingredient, foundIngredient);
    }

    @Test
    public void updateIngredientQuantity(){
        dbHandler = new DatabaseHandler(addIngredient);
        boolean valueInserted = dbHandler.addHandle(ingredient);
        boolean quantityUpdated = dbHandler.topUpQuantity(ingredient);

                PantryIngredient foundIngredient = new PantryIngredient();
        if(valueInserted){
            Object obj = dbHandler.findHandle(ingredient.getIngredientID(), "PantryIngredient");
            foundIngredient = (PantryIngredient) obj;
        }
        int comparedValue = ingredient.getTotalQuantity() + ingredient.getCurrentQuantity();

        assertEquals(comparedValue, foundIngredient.getCurrentQuantity());

    }

}
