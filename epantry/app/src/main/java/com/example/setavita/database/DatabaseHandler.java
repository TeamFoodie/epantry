package com.example.setavita.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.setavita.models.PantryIngredient;
import com.example.setavita.models.User;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PANTRYDB";

    //table names
    private static final String TABLE_INGREDIENT = "PantryIngredients";
    private static final String TABLE_USERS = "PantryUser";

//    //columns for Ingredient Table
    private static final String INGREDIENT_ID = "IngredientID";
    private static final String INGREDIENT_NAME = "IngredientName";
    private static final String TOTAL_QUANTITY = "TotalQuantity";
    private static final String CURRENT_QUANTITY = "CurrentQuantity";
    private static final String UNIT_MEASURE = "UnitMeasure";
    private static final String OWNER = "Owner";

    //columns for User Table
    private static final String USER_ID = "id";
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "email";
    private static final String[] USER_COLUMNS = {USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL};

    private ManageTables table = new ManageTables();


    //initialize the database
    public DatabaseHandler(Context context)

    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table.createIngredientTable(TABLE_INGREDIENT));
        db.execSQL(table.createUserTable(TABLE_USERS));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

//    public PantryIngredient loadHandler() {//} ArrayList<PantryIngredient> loadHandler() {
//
//        ArrayList<PantryIngredient> ingredient = new ArrayList<>();
//        PantryIngredient current = null;
////        String result = "";
//        String query = "Select* FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        while (cursor.moveToNext()) {
//            String result_0 = cursor.getString(0);
//            String result_1 = cursor.getString(1);
//            int result_2 = cursor.getInt(2);
//            int result_3 = cursor.getInt(3);
//            String result_4 = cursor.getString(4);
//            int result_5 = cursor.getInt(5);
//
//
//
//            current = new PantryIngredient(result_0, result_1, result_2, result_3, result_4, result_5);
//            ingredient.add(current);
//            return current;
//
////            result += String.valueOf(result_0) + " " + result_1 +
////                    String.valueOf(result_2) + " " +
////                    String.valueOf(result_3) + " " +
////                    String.valueOf(result_4) + " " +
////                    String.valueOf(result_5) + " " +
////                    System.getProperty("line.separator");
//        }
//        cursor.close();
//        db.close();
////        return result;
//
//        return current;
//    }


    public boolean addHandle(Object object) {
        boolean createSuccessful = false;
        ContentValues values = null;
        String tableName = "";


        if (object instanceof PantryIngredient) {
            PantryIngredient ingredientObject = (PantryIngredient) object;
            tableName = TABLE_INGREDIENT;
            values = table.getIngredientContents(ingredientObject);


        }
        else if(object instanceof User){
            User userObject = (User) object;
            tableName = TABLE_USERS;
            values = table.getUserContents(userObject);
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


    public Object findHandle(String id, String tableName) {
        Object object = new Object();
        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        switch (tableName) {
            case "PantryIngredient":
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                PantryIngredient foundIngredient = table.findIngredient(cursor);
                object = (Object) foundIngredient;

                break;
            case "User":
                query = "Select * FROM " + TABLE_USERS + " WHERE UserID" + " = " + "'" + id + "'";
                break;
            default:
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
//                cursor = getReadableDatabase().rawQuery(query, null);
//                PantryIngredient foundIngredient = table.findIngredient(cursor);
//                object = (Object) foundIngredient;
                break;
        }

//        String query = "Select * FROM " + TABLE_NAME + " WHERE " + INGREDIENT_ID + " = " + "'" + ingredientID + "'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = getReadableDatabase().rawQuery(query, null);
//        Cursor cursor1 = db.ra
//        PantryIngredient pantryIngredient = new PantryIngredient();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            pantryIngredient.setIngredientID(cursor.getString(0));
//            pantryIngredient.setIngredientName(cursor.getString(1));
//            pantryIngredient.setTotalQuantity(Integer.parseInt(cursor.getString(2)));
//            pantryIngredient.setCurrentQuantity(Integer.parseInt(cursor.getString(3)));
//            pantryIngredient.setUnitMeasure(cursor.getString(4));
//            pantryIngredient.setOwner(Integer.parseInt(cursor.getString(5)));
//            cursor.close();
//        } else {
//            pantryIngredient = null;
//        }

//        System.out.println("Found: " + pantryIngredient.getIngredientName());
        db.close();
        return object;
    }

//    public boolean deleteHandler(String ID) {
//        boolean result = false;
//        String query = "Select*FROM" + TABLE_NAME + "WHERE" + INGREDIENT_ID + "= '" + String.valueOf(ID) + "'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        PantryIngredient pantryIngredient = new PantryIngredient();
//        if (cursor.moveToFirst()) {
//            pantryIngredient.setIngredientID(cursor.getString(0));
//            db.delete(TABLE_NAME, INGREDIENT_ID + "=?",
//                    new String[]{
//                            String.valueOf(pantryIngredient.getIngredientID())
//                    });
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
////    }
//
    public boolean updateQuantity(PantryIngredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURRENT_QUANTITY, ingredient.getCurrentQuantity());
        return db.update(TABLE_INGREDIENT, values, INGREDIENT_ID + "=" + ingredient.getIngredientID(), null) > 0;

    }


    public boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE username = '" + username + "' AND password = '" + password + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //
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

    public boolean checkExistingUser(String username) {
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE username = '" + username + "'";

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

    public User getUser(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_USERS, // a. table
                        USER_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setID(Integer.parseInt(cursor.getString(0)));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));

        Log.d("getUser(" + id + ")", user.toString());

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();

        String query = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));

                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());

        return users;
    }

    public int updateUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());

        int i = db.update(TABLE_USERS, //table
                values, // column/value
                USER_ID + " = ?", // selections
                new String[]{String.valueOf(user.getUserID())}); //selection args

        db.close();

        return i;
    }
}