package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

public class PreferencesTable {
    //columns for Ingredient Table
    public final String USER_ID = "UserID";
    public final String SPICES = "Spices";
    public final String POULTRY = "Poultry";
    public final String STAPLE = "Staple";
    public final String VEGETABLES = "Vegetables";
    public final String MEATS = "Meats";
    public final String SAUCES = "Sauces";
    public final String OILS = "Oils";

    //Returns create string for creating Pantry ingredient table.
    public String createPreferencesTable(String TABLE_NAME) {
        String createTable =  "CREATE TABLE " + TABLE_NAME +
                "(" + USER_ID + " INTEGER NOT NULL," +
                SPICES + " INTEGER," +
                POULTRY + " INTEGER," +
                STAPLE + " INTEGER," +
                VEGETABLES + " INTEGER," +
                MEATS + " INTEGER," +
                SAUCES + " INTEGER," +
                OILS + " INTEGER" + ");";

        return createTable;
    }

    /**
     * Method takes in a PantryIngredient object allocate them appropriate into the ContentValue
     * format for query execution.
     *
     * @param pIngredients
     * @return
     */
    public ContentValues getThresholds(ArrayList<Integer> thresholds) {
        ContentValues values = new ContentValues();
        values.put(USER_ID, thresholds.get(0));
        values.put(SPICES, thresholds.get(1));
        values.put(POULTRY, thresholds.get(2));
        values.put(STAPLE, thresholds.get(3));
        values.put(VEGETABLES, thresholds.get(4));
        values.put(MEATS, thresholds.get(5));
        values.put(SAUCES, thresholds.get(6));
        values.put(OILS, thresholds.get(7));
        return values;
    }

    /**
     * Result cursor from database is passed through parameters and used to set pantry ingredient
     * details and then return a PantryIngredient type objectd
     *
     * @param cursor
     * @return
     */
    public ArrayList<Integer> findThresholds(Cursor cursor){
        ArrayList<Integer> thresholds = new ArrayList<>();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            thresholds.add(cursor.getInt(0));
            thresholds.add(cursor.getInt(1));
            thresholds.add(cursor.getInt(2));
            thresholds.add(cursor.getInt(3));
            thresholds.add(cursor.getInt(4));
            thresholds.add(cursor.getInt(5));
            thresholds.add(cursor.getInt(6));
            thresholds.add(cursor.getInt(7));
            cursor.close();
        }else{
            return null;
        }

        return thresholds;
    }

    /**
     * Cursor from database query is used to compile a PantryIngredient
     * type array and insert objects into array and reutrn the array.
     * @param cursor
     * @return
     */
//    public List<PantryIngredient> loadAllPantryIngredients(Cursor cursor){
//        List<PantryIngredient> pantryList = new ArrayList<>();
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            PantryIngredient pantryObject = new PantryIngredient();
//            pantryObject.setIngredientID(cursor.getString(0));
//            pantryObject.setIngredientName(cursor.getString(1));
//            pantryObject.setTotalQuantity(cursor.getInt(2));
//            pantryObject.setCurrentQuantity(cursor.getInt(3));
//            pantryObject.setUnitMeasure(cursor.getString(4));
//            pantryObject.setFoodGroup(cursor.getString(5));
//            pantryObject.setOwner(cursor.getInt(6));
//            pantryList.add(pantryObject);
//            System.out.println("THIS IS THE PANTRY INGREDIENT TABLE");
//            System.out.println(pantryObject.toString());
//            cursor.moveToNext();
//        }
//
//        return  pantryList;
//    }
//
//    /**
//     * method takes in PantryIngredient object and congiures content value
//     * to include only the quantity needed for db update
//     * @param ingredient
//     * @return
//     */
//    public ContentValues updateQuantity(PantryIngredient ingredient){
//        ContentValues values = new ContentValues();
//        values.put(STAPLE, ingredient.getCurrentQuantity());
//
//        return values;
//    }

}