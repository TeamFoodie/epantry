package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

public class PantryIngredientTable {
    //columns for Ingredient Table
    public final String INGREDIENT_ID = "IngredientID";
    public final String INGREDIENT_NAME = "IngredientName";
    public final String TOTAL_QUANTITY = "TotalQuantity";
    public final String CURRENT_QUANTITY = "CurrentQuantity";
    public final String UNIT_MEASURE = "UnitMeasure";
    public final String FOOD_GROUP = "FoodGroup";
    public final String OWNER = "Owner";

    //Returns create string for creating Pantry ingredient table.
    public String createIngredientTable(String TABLE_NAME) {
        String createTable =  "CREATE TABLE " + TABLE_NAME +
                "(" + INGREDIENT_ID + " NVARCHAR PRIMARY KEY," +
                INGREDIENT_NAME + " NVARCHAR," +
                TOTAL_QUANTITY + " INTEGER," +
                CURRENT_QUANTITY + " INTEGER," +
                UNIT_MEASURE + " NVARCHAR," +
                FOOD_GROUP + " NVARCHAR," +
                OWNER + " INTEGER" + ");";

        return createTable;
    }

    /**
     * Method takes in a PantryIngredient object allocate them appropriate into the ContentValue
     * format for query execution.
     *
     * @param pIngredients
     * @return
     */
    public ContentValues getIngredientContents(PantryIngredient pIngredients) {
        ContentValues values = new ContentValues();
        values.put(INGREDIENT_ID , pIngredients.getIngredientID());
        values.put(INGREDIENT_NAME, pIngredients.getIngredientName());
        values.put(TOTAL_QUANTITY, pIngredients.getTotalQuantity());
        values.put(CURRENT_QUANTITY, pIngredients.getCurrentQuantity());
        values.put(UNIT_MEASURE, pIngredients.getUnitMeasure());
        values.put(FOOD_GROUP, pIngredients.getFoodGroup());
        values.put(OWNER, pIngredients.getOwner());
        return values;
    }

    /**
     * Result cursor from database is passed through parameters and used to set pantry ingredient
     * details and then return a PantryIngredient type objectd
     *
     * @param cursor
     * @return
     */
    public PantryIngredient findIngredient(Cursor cursor){
        PantryIngredient ingredient = new PantryIngredient();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ingredient.setIngredientID(cursor.getString(0));
            ingredient.setIngredientName(cursor.getString(1));
            ingredient.setTotalQuantity(cursor.getInt(2));
            ingredient.setCurrentQuantity(cursor.getInt(3));
            ingredient.setUnitMeasure(cursor.getString(4));
            ingredient.setFoodGroup(cursor.getString(5));
            ingredient.setOwner(cursor.getInt(6));
            cursor.close();
        }else{
            return null;
        }

        return ingredient;
    }

    /**
     * Cursor from database query is used to compile a PantryIngredient
     * type array and insert objects into array and reutrn the array.
     * @param cursor
     * @return
     */
    public List<PantryIngredient> loadAllPantryIngredients(Cursor cursor){
        List<PantryIngredient> pantryList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PantryIngredient pantryObject = new PantryIngredient();
            pantryObject.setIngredientID(cursor.getString(0));
            pantryObject.setIngredientName(cursor.getString(1));
            pantryObject.setTotalQuantity(cursor.getInt(2));
            pantryObject.setCurrentQuantity(cursor.getInt(3));
            pantryObject.setUnitMeasure(cursor.getString(4));
            pantryObject.setFoodGroup(cursor.getString(5));
            pantryObject.setOwner(cursor.getInt(6));
            pantryList.add(pantryObject);
            System.out.println("THIS IS THE PANTRY INGREDIENT TABLE");
            System.out.println(pantryObject.toString());
            cursor.moveToNext();
        }

        return  pantryList;
    }

    /**
     * method takes in PantryIngredient object and congiures content value
     * to include only the quantity needed for db update
     * @param ingredient
     * @return
     */
    public ContentValues updateQuantity(PantryIngredient ingredient){
        ContentValues values = new ContentValues();
        values.put(CURRENT_QUANTITY, ingredient.getCurrentQuantity());

        return values;
    }

}