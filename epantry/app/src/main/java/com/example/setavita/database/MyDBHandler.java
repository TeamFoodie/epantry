package com.example.setavita.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.setavita.models.PantryIngredients;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "epantry.db";
    public static final String TABLE_NAME = "PantryIngredients";
    public static final String COLUMN_ID = "IngredientID";
    public static final String COLUMN_NAME = "IngredientName";
    public static final String COLUMN_NAME1 = "TotalQuantity";
    public static final String COLUMN_NAME2 = "CurrentQuantity";
    public static final String COLUMN_NAME3 = "Owner";

    //initialize the database
    public MyDBHandler(Context context)

    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + "TEXT PRIMARYKEY," +
                COLUMN_NAME + "TEXT, " +
                COLUMN_NAME1 + "INTEGER, " +
                COLUMN_NAME2 + "INTEGER, " +
                COLUMN_NAME3 + "INTEGER " + ")";
        db.execSQL(CREATE_TABLE);
    }


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
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            int result_4 = cursor.getInt(4);
            result += String.valueOf(result_0) + " " + result_1 +
                    String.valueOf(result_2) + " " +
                    String.valueOf(result_3) + " " +
                    String.valueOf(result_4) + " " +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;

    }


    public boolean addHandler(PantryIngredients pIngredients) {
        boolean createSuccessful = false;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, pIngredients.getIngredientID());
        values.put(COLUMN_NAME, pIngredients.getIngredientName());
        values.put(COLUMN_NAME1, pIngredients.getTotalQuantity());
        values.put(COLUMN_NAME2, pIngredients.getCurrentQuantity());
        values.put(COLUMN_NAME3, pIngredients.getOwner());

        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(TABLE_NAME, null, values);
        if (i == -1){
            createSuccessful = false;
        }else{
            createSuccessful = true;
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }

    public PantryIngredients findHandler(String ingredientID) {

        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_ID + " = " + "'" + ingredientID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PantryIngredients pantryIngredients = new PantryIngredients();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            pantryIngredients.setIngredientID(cursor.getString(0));
            pantryIngredients.setIngredientName(cursor.getString(1));
            pantryIngredients.setTotalQuantity(Integer.parseInt(cursor.getString(2)));
            pantryIngredients.setCurrentQuantity(Integer.parseInt(cursor.getString(3)));
            pantryIngredients.setOwner(Integer.parseInt(cursor.getString(4)));
            cursor.close();
        } else {
            pantryIngredients = null;
        }
        db.close();
        return pantryIngredients;
    }

    public boolean deleteHandler(String ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PantryIngredients pantryIngredients = new PantryIngredients();
        if (cursor.moveToFirst()) {
            pantryIngredients.setIngredientID(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[]{
                            String.valueOf(pantryIngredients.getIngredientID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(String ID, String name, int total, int current, int owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_NAME1, total);
        args.put(COLUMN_NAME2, current);
        args.put(COLUMN_NAME3, owner);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}
