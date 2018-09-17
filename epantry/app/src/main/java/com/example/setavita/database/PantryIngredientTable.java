package com.example.setavita.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.setavita.models.PantryIngredients;

public class PantryIngredientTable {

    private String TABLE_NAME = "Ingredient";
    private String INGREDIENT_ID = "IngredientID";
    private String INGREDIENT_NAME = "IngredientName";
    private String TOTAL_QUANTITY = "TotalQuantity";
    private String CURRENT_QUANTITY = "CurrentQuantity";
    private String UNIT_MEASURE = "UnitMeasure";
    private String OWNER = "Owner";
    private String createTable;
    private ContentValues values;



    public PantryIngredientTable(){
        this.createTable =  "CREATE TABLE " + TABLE_NAME +
                "(" + INGREDIENT_ID + " NVARCHAR PRIMARY KEY," +
                INGREDIENT_NAME + " NVARCHAR," +
                TOTAL_QUANTITY + " INTEGER," +
                CURRENT_QUANTITY + " INTEGER," +
                UNIT_MEASURE + " NVARCHAR," +
                OWNER + " INTEGER" + ");";

        this.values = new ContentValues();

    }

    public String getCreateTable() {
        return createTable;
    }

    public String getAllIngredients(){
        return "";
    }

    public ContentValues getValues(PantryIngredients pIngredients){
        values.put(INGREDIENT_ID , pIngredients.getIngredientID());
        values.put(INGREDIENT_NAME, pIngredients.getIngredientName());
        values.put(TOTAL_QUANTITY, pIngredients.getTotalQuantity());
        values.put(CURRENT_QUANTITY, pIngredients.getCurrentQuantity());
        values.put(UNIT_MEASURE, pIngredients.getUnitMeasure());
        values.put(OWNER, pIngredients.getOwner());

        return values;
    }


    public PantryIngredients findIngredient(Cursor cursor){
        PantryIngredients pantryIngredients = new PantryIngredients();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            pantryIngredients.setIngredientID(cursor.getString(0));
            pantryIngredients.setIngredientName(cursor.getString(1));
            pantryIngredients.setTotalQuantity(Integer.parseInt(cursor.getString(2)));
            pantryIngredients.setCurrentQuantity(Integer.parseInt(cursor.getString(3)));
            pantryIngredients.setUnitMeasure(cursor.getString(4));
            pantryIngredients.setOwner(Integer.parseInt(cursor.getString(5)));
            cursor.close();
        } else {
            pantryIngredients = null;
        }

        return pantryIngredients;
    }
}

