package com.example.setavita.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.setavita.models.PantryIngredient;
import com.example.setavita.models.User;

import java.util.ArrayList;
import java.util.List;

public class PantryIngredientTable {



    //columns for Ingredient Table
    public static final String INGREDIENT_ID = "IngredientID";
    public static final String INGREDIENT_NAME = "IngredientName";
    public static final String TOTAL_QUANTITY = "TotalQuantity";
    public static final String CURRENT_QUANTITY = "CurrentQuantity";
    public static final String UNIT_MEASURE = "UnitMeasure";
    public static final String OWNER = "Owner";



    //Details for Cart



    public String createIngredientTable(String TABLE_NAME) {
        String createTable =  "CREATE TABLE " + TABLE_NAME +
                "(" + INGREDIENT_ID + " NVARCHAR PRIMARY KEY," +
                INGREDIENT_NAME + " NVARCHAR," +
                TOTAL_QUANTITY + " INTEGER," +
                CURRENT_QUANTITY + " INTEGER," +
                UNIT_MEASURE + " NVARCHAR," +
                OWNER + " INTEGER" + ");";

        return createTable;
    }


    public ContentValues getIngredientContents(PantryIngredient pIngredients) {
        ContentValues values = new ContentValues();
        values.put(INGREDIENT_ID , pIngredients.getIngredientID());
        values.put(INGREDIENT_NAME, pIngredients.getIngredientName());
        values.put(TOTAL_QUANTITY, pIngredients.getTotalQuantity());
        values.put(CURRENT_QUANTITY, pIngredients.getCurrentQuantity());
        values.put(UNIT_MEASURE, pIngredients.getUnitMeasure());
        values.put(OWNER, pIngredients.getOwner());

        System.out.println("PANTRY table!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return values;
    }





    public PantryIngredient findIngredient(Cursor cursor){
        PantryIngredient ingredient = new PantryIngredient();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            ingredient.setIngredientID(cursor.getString(0));
            ingredient.setIngredientName(cursor.getString(1));
            ingredient.setTotalQuantity(cursor.getInt(2));
            ingredient.setCurrentQuantity(cursor.getInt(3));
            ingredient.setUnitMeasure(cursor.getString(4));
            ingredient.setOwner(cursor.getInt(5));
            cursor.close();
        }else{
            return null;
        }

        return ingredient;
    }

    public List<PantryIngredient> loadAllPantryIngredients(Cursor cursor){
        List<PantryIngredient> pantryList = new ArrayList<>();
        PantryIngredient pantryObject = new PantryIngredient();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pantryObject.setIngredientID(cursor.getString(0));
            pantryObject.setIngredientName(cursor.getString(1));
            pantryObject.setTotalQuantity(cursor.getInt(2));
            pantryObject.setCurrentQuantity(cursor.getInt(3));
            pantryObject.setUnitMeasure(cursor.getString(4));
            pantryObject.setOwner(cursor.getInt(5));
            pantryList.add(pantryObject);

            cursor.moveToNext();
        }

        return  pantryList;
    }

}