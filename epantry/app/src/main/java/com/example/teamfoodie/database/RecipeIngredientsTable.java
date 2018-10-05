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

//    public ContentValues addNewRecipe(Recipe recipeObject) {
//        ContentValues values = new ContentValues();
//        values.put(RECIPE_NAME, recipeObject.getRecipeName());
//        values.put(RECIPE_DESCRIPTION, recipeObject.getDescription());
//        values.put(RECIPE_PHOTO, recipeObject.getPhoto());
//        values.put(CALORIE_COUNT, recipeObject.getCalorieCount());
//        values.put(COOKING_TIME, recipeObject.getCookingTime());
//        values.put(AUTHOR, recipeObject.getAuthor());
//        values.put(NUMBER_OF_PEOPLE, recipeObject.getNumberOfPeople());
//        values.put(FOODGROUP, recipeObject.getDietary());
////        values.put(RECIPE_URL, recipeObject.getURL());
//        return values;
//    }

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


}
