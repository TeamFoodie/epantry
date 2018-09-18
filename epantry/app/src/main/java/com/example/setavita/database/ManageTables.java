package com.example.setavita.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.setavita.models.PantryIngredient;
import com.example.setavita.models.User;

public class ManageTables {



    //columns for Ingredient Table
    public static final String INGREDIENT_ID = "IngredientID";
    public static final String INGREDIENT_NAME = "IngredientName";
    public static final String TOTAL_QUANTITY = "TotalQuantity";
    public static final String CURRENT_QUANTITY = "CurrentQuantity";
    public static final String UNIT_MEASURE = "UnitMeasure";
    public static final String OWNER = "Owner";

    //columns for User Table
    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "UserName";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_EMAIL = "Email";
    private static final String[] USER_COLUMNS = {USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL};

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

    public String createUserTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, "+
                USER_PASSWORD + " TEXT, "+
                USER_EMAIL + " TEXT );";

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

    public ContentValues getUserContents(User userObject) {
        ContentValues values = new ContentValues();
            values.put(USER_NAME, userObject.getUsername());
            values.put(USER_PASSWORD, userObject.getPassword());
            values.put(USER_EMAIL, userObject.getEmail());


        System.out.println("User table!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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


}