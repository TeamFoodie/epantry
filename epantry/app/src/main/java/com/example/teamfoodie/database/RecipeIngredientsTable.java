package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientsTable {

    //columns for Ingredient Table
    public static final String RECIPE_ID = "RecipeID";
    public static final String INGREDIENT_NAME = "IngredientName";
    public static final String MEASUREMENT = "Measurement";
    public static final String UNIT_COUNT = "UnitCount";


    public String createRecipeIngredientTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + RECIPE_ID + " INTEGER NOT NULL," +
                INGREDIENT_NAME + " NVARCHAR," +
                MEASUREMENT + " INTEGER," +
                UNIT_COUNT + " NVARCHAR);";
        return createTable;
    }

    public ContentValues getIngredientContents(Ingredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, ingredient.getRecipeID());
        values.put(INGREDIENT_NAME, ingredient.getName());
        values.put(MEASUREMENT, ingredient.getMeasurement());
        values.put(UNIT_COUNT, ingredient.getUnitCount());
        return values;
    }


    public List<Ingredient> loadAllRecipeIngredients(Cursor cursor) {
        List<Ingredient> ingredientList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ingredient ingredient = new Ingredient(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            ingredientList.add(ingredient);
            System.out.println("passed through load all recipe ingredients");
            System.out.println(ingredient.toString());
            cursor.moveToNext();
        }

        return ingredientList;
    }

    public static List<Object> calculateNewMeasurements(List<Object> currentList, int currentServes, int newServes) {
        List<Object> newList = new ArrayList<>();
        List<Ingredient> ingredientList = new ArrayList<>();


        //if current Serves is greater than 1 then divide all ingredients to ensure you get just enough to serve one!
        if (currentServes > 1) {

            System.out.println("this is if exist servies is > 1");
            for (int i = 0; i < currentList.size(); i++) {
                Ingredient ingredient = (Ingredient) currentList.get(i);
                System.out.println("OLD MEASUREMENT IS " + ingredient.getMeasurement());

                double newMeasurement = ingredient.getMeasurement() / currentServes;
                System.out.println("setting serves is 1 gives " + newMeasurement);
                ingredient.setMeasurement(newMeasurement);
                ingredientList.add(ingredient);
            }

            //ELSE if currentServes is = 1 then not need to divide - convert directly to Ingredient type and add to Ingredient Type arraylist
        }else{
            for (int i = 0; i < currentList.size(); i++){
                ingredientList.add((Ingredient) currentList.get(i));
            }
        }

        System.out.println("for actual calculation");
        for (int i = 0; i < ingredientList.size(); i++) {
            double newMeasurement = ingredientList.get(i).getMeasurement() * newServes;
            System.out.println("after multiplying you get " + newMeasurement);
            ingredientList.get(i).setMeasurement(newMeasurement);
            newList.add((Object) ingredientList.get(i));
        }

//        newList.addAll(ingredientList);
//        }


        return newList;
    }

}
