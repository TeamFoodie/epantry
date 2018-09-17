package com.example.setavita.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import com.example.setavita.models.PantryIngredients;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "epantry";
    public static final String TABLE_NAME = "Ingredient";
    public static final String INGREDIENT_ID = "IngredientID";
    public static final String INGREDIENT_NAME = "IngredientName";
    public static final String TOTAL_QUANTITY = "TotalQuantity";
    public static final String CURRENT_QUANTITY = "CurrentQuantity";
    public static final String UNIT_MEASURE = "UnitMeasure";
    public static final String OWNER = "Owner";
    public static final int version = 1;
//    public MySQLiteHelper db = new MySQLiteHelper(this);

    //initialize the database
    public MyDBHandler(Context context)

    {
        super(context, TABLE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + INGREDIENT_ID + " NVARCHAR PRIMARY KEY," +
                INGREDIENT_NAME + " NVARCHAR," +
                TOTAL_QUANTITY + " INTEGER," +
                CURRENT_QUANTITY + " INTEGER," +
                UNIT_MEASURE + " NVARCHAR," +
                OWNER + " INTEGER" + ");";
        db.execSQL(CREATE_TABLE);
    }

//    public void onCreate(Bundle savedInstance){
//        addHandler()
//    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String loadHandler() {

        String result = "";
        String query = "Select* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            String result_4 = cursor.getString(4);
            int result_5 = cursor.getInt(5);
            result += String.valueOf(result_0) + " " + result_1 +
                    String.valueOf(result_2) + " " +
                    String.valueOf(result_3) + " " +
                    String.valueOf(result_4) + " " +
                    String.valueOf(result_5) + " " +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;

    }


    public boolean addIngredient(PantryIngredients pIngredients) {
        boolean createSuccessful = false;
        ContentValues values = new ContentValues();
        System.out.println(pIngredients.toString());
        System.out.println(pIngredients.getIngredientID());
        values.put(INGREDIENT_ID , pIngredients.getIngredientID());
        values.put(INGREDIENT_NAME, pIngredients.getIngredientName());
        values.put(TOTAL_QUANTITY, pIngredients.getTotalQuantity());
        values.put(CURRENT_QUANTITY, pIngredients.getCurrentQuantity());
        values.put(UNIT_MEASURE, pIngredients.getUnitMeasure());
        values.put(OWNER, pIngredients.getOwner());

        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(TABLE_NAME, null, values);


        if (i == -1){
            createSuccessful = false;
            System.out.println("could not populate");
        }else{
            createSuccessful = true;
            System.out.println("table populated");
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }

    public PantryIngredients findHandler(String ingredientID) {

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + INGREDIENT_ID + " = " + "'" + ingredientID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
//        Cursor cursor1 = db.ra
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

//        System.out.println("Found: " + pantryIngredients.getIngredientName());
        db.close();
        return pantryIngredients;
    }

    public boolean deleteHandler(String ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + INGREDIENT_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PantryIngredients pantryIngredients = new PantryIngredients();
        if (cursor.moveToFirst()) {
            pantryIngredients.setIngredientID(cursor.getString(0));
            db.delete(TABLE_NAME, INGREDIENT_ID + "=?",
                    new String[]{
                            String.valueOf(pantryIngredients.getIngredientID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateQuantity(PantryIngredients ingredient){
//        boolean updated = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURRENT_QUANTITY, ingredient.getCurrentQuantity());
        return db.update(TABLE_NAME, values, INGREDIENT_ID + "=" + ingredient.getIngredientID(), null) > 0;


//        return updated;
    }

    public boolean updateHandler(String ID, String name, int total, int current, int owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(INGREDIENT_ID, ID);
        args.put(INGREDIENT_NAME, name);
        args.put(TOTAL_QUANTITY, total);
        args.put(CURRENT_QUANTITY, current);
        args.put(OWNER, owner);
        return db.update(TABLE_NAME, args, INGREDIENT_ID + "=" + ID, null) > 0;
    }
}