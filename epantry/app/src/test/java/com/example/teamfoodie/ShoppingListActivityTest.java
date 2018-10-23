package com.example.teamfoodie;

import com.example.teamfoodie.epantry.ShoppingListActivity;
import com.example.teamfoodie.epantry.SignupFormActivity;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ShoppingListActivityTest {

        private ShoppingListActivity shoppinglistClass;

        @Before
        public void setUp() {
            shoppinglistClass = new ShoppingListActivity();
        }

        @After
        public void tearDown() {
        }

        /**
         * Test of calculateLowStock method, of class ShoppingListActivity. Expected to return an empty ArrayList.
         */
        @Test
        public void testCalculateLowStockReturnsEmpty() {

            List<PantryIngredient> pantryList = new ArrayList<>();

            assertTrue(shoppinglistClass.calculateLowStock(pantryList).isEmpty());
        }

    /**
     * Test of calculateLowStock method, of class ShoppingListActivity.
     * Expected to return an ArrayList with elements
     */
    @Test
    public void testCalculateLowStockReturnsNotEmpty() {

        List<PantryIngredient> pantryList = new ArrayList<>();
        PantryIngredient ingredients = new PantryIngredient();
        ingredients.setCurrentQuantity(3);
        ingredients.setTotalQuantity(5);

        for(int i = 0; i < 5; ++i) {
            pantryList.add(ingredients);
        }

        assertFalse(shoppinglistClass.calculateLowStock(pantryList).isEmpty());
    }

    /**
     * Test of calculateLowStock method, of class ShoppingListActivity.
     * 4 out of 5 ingredients are "low-stock". Expected to return ArrayList of size 5.
     */
    @Test
    public void testCalculateLowStockReturnsFour() {

        List<PantryIngredient> pantryList = new ArrayList<>();
        PantryIngredient ingredients1 = new PantryIngredient();
        PantryIngredient ingredients2 = new PantryIngredient();
        PantryIngredient ingredients3 = new PantryIngredient();
        PantryIngredient ingredients4 = new PantryIngredient();
        PantryIngredient ingredients5 = new PantryIngredient();

        ingredients1.setCurrentQuantity(4);
        ingredients1.setTotalQuantity(5);
        ingredients2.setCurrentQuantity(10);
        ingredients2.setTotalQuantity(20);
        ingredients3.setCurrentQuantity(1);
        ingredients3.setTotalQuantity(2);
        ingredients4.setCurrentQuantity(68);
        ingredients4.setTotalQuantity(90);
        ingredients5.setCurrentQuantity(5);
        ingredients5.setTotalQuantity(5);

        pantryList.add(ingredients1);
        pantryList.add(ingredients2);
        pantryList.add(ingredients3);
        pantryList.add(ingredients4);
        pantryList.add(ingredients5);

        assertEquals(4, shoppinglistClass.calculateLowStock(pantryList).size());
    }
}
