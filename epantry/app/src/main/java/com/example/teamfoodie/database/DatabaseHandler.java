package com.example.teamfoodie.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EPantry";

    //table names
    private static final String TABLE_INGREDIENT = "PantryIngredients";
    private static final String TABLE_USERS = "Users";


    private PantryIngredientTable pantryIngredientTable = new PantryIngredientTable();
    private UserTable userTable = new UserTable();

    private String username = "";
    private String password = "";


    //initialize the database
    public DatabaseHandler(Context context)

    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(pantryIngredientTable.createIngredientTable(TABLE_INGREDIENT));
        db.execSQL(userTable.createUserTable(TABLE_USERS));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }


    public boolean addHandle(Object object) {
        boolean createSuccessful = false;
        ContentValues values = null;
        String tableName = "";


        if (object instanceof PantryIngredient) {
            PantryIngredient ingredientObject = (PantryIngredient) object;
            tableName = TABLE_INGREDIENT;
            values = pantryIngredientTable.getIngredientContents(ingredientObject);
        } else if (object instanceof User) {
            User userObject = (User) object;
            tableName = TABLE_USERS;
            values = userTable.addNewUser(userObject);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(tableName, null, values);


        if (i == -1) {
            createSuccessful = false;
            System.out.println("could not populate");
        } else {
            createSuccessful = true;
            System.out.println("table populated");
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }

    public void setUser(String user, String pass) {
        this.username = user;
        this.password = pass;

    }

    public Object findHandle(String id, String tableName) {
        Object object = new Object();
        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        PantryIngredient foundIngredient;
        User foundUser;

        switch (tableName) {
            case "PantryIngredient":
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);
                System.out.println("FIND HANDLE FOR PANTRY INGREDIENT!!!!!!");
                object = (Object) foundIngredient;

                break;
            case "User":
                query = "SELECT * FROM " + TABLE_USERS + " WHERE UserName = '" + username + "' AND Password = '" + password + "'";

                System.out.println("user found : name: " + username + " password: " + password);
                cursor = db.rawQuery(query, null);
                foundUser = userTable.findUser(cursor);
                object = (Object) foundUser;
                break;
            default:
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);
                object = (Object) foundIngredient;
                break;
        }
        db.close();
        return object;
    }

    public boolean updateQuantity(PantryIngredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values = pantryIngredientTable.updateQuantity(ingredient);
        String query = "IngredientID =" + ingredient.getIngredientID();
        return db.update(TABLE_INGREDIENT, values, query, null) > 0;

    }

    public List<PantryIngredient> loadAllPantryIngredients(int id) {
        List<PantryIngredient> ingredientList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_INGREDIENT + " WHERE Owner" + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        ingredientList = pantryIngredientTable.loadAllPantryIngredients(cursor);


        return ingredientList;
    }


    public boolean checkExistingUser(String username) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE username = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }
}