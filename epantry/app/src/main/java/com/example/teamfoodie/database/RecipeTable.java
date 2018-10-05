package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeTable {

    private String username;
    private String password;

    //columns for User Table
    private static final String RECIPE_ID = "RecipeID";
    private static final String RECIPE_NAME = "Name";
    private static final String RECIPE_DESCRIPTION = "Description";
    private static final String RECIPE_PHOTO = "Photo";
    private static final String CALORIE_COUNT = "CalorieCount";
    private static final String COOKING_TIME = "CookingTime";
    private static final String AUTHOR = "Author";
    private static final String NUMBER_OF_PEOPLE = "NumberOfPeople";
    private static final String FOODGROUP = "Dietary";

    public String createRecipeTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RECIPE_NAME + " TEXT, "+
                RECIPE_DESCRIPTION + " TEXT,"+
                RECIPE_PHOTO + " INTEGER," +
                CALORIE_COUNT + " INTEGER," +
                COOKING_TIME + " INTEGER," +
                AUTHOR + " INTEGER," +
                NUMBER_OF_PEOPLE + " INTEGER," +
                FOODGROUP + " TEXT );";

        return createTable;
    }

    /**
     *  Method takes in a Recipe object allocate them appropriate into the ContentValue
     * format for query execution.
     *
     * @param recipeObject
     * @return
     */

    public ContentValues addNewRecipe(Recipe recipeObject) {
        ContentValues values = new ContentValues();
        values.put(RECIPE_NAME, recipeObject.getRecipeName());
        values.put(RECIPE_DESCRIPTION, recipeObject.getDescription());
        values.put(RECIPE_PHOTO, recipeObject.getPhoto());
        values.put(CALORIE_COUNT, recipeObject.getCalorieCount());
        values.put(COOKING_TIME, recipeObject.getCookingTime());
        values.put(AUTHOR, recipeObject.getAuthor());
        values.put(NUMBER_OF_PEOPLE, recipeObject.getNumberOfPeople());
        values.put(FOODGROUP, recipeObject.getDietary());
//        values.put(RECIPE_URL, recipeObject.getURL());
        return values;
    }

    /**
     * Result cursor from database is passed through parameters and used to set recipe
     * details and then return a PantryIngredient type object
     *
     * @param cursor
     * @return
     */
    public Recipe findRecipe(Cursor cursor) {
        Recipe existingRecipe = new Recipe();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            existingRecipe.setRecipeID(cursor.getInt(0));
            existingRecipe.setRecipeName(cursor.getString(1));
            existingRecipe.setDescription(cursor.getString(2));
            existingRecipe.setPhoto(cursor.getInt(3));
            existingRecipe.setCalorieCount(cursor.getInt(4));
            existingRecipe.setCookingTime(cursor.getInt(5));
            existingRecipe.setAuthor(cursor.getInt(6));
            existingRecipe.setNumberOfPeople(cursor.getInt(7));
            existingRecipe.setDietary(cursor.getString(8));
            cursor.close();
        } else {
            return null;
        }

        return existingRecipe;
    }

    /**
     * method takes in result cursor for database and populates recipe objects to be
     * added to array list before return Recipe type object
     * @param cursor
     * @return
     */
    public List<Recipe> loadAllRecipes(Cursor cursor){
        List<Recipe> recipeList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Recipe recipeObject = new Recipe();
            recipeObject.setRecipeID(cursor.getInt(0));
            recipeObject.setRecipeName(cursor.getString(1));
            recipeObject.setDescription(cursor.getString(2));
            recipeObject.setPhoto(cursor.getInt(3));
            recipeObject.setCalorieCount(cursor.getInt(4));
            recipeObject.setCookingTime(cursor.getInt(5));
            recipeObject.setAuthor(cursor.getInt(6));
            recipeObject.setNumberOfPeople(cursor.getInt(7));
            recipeObject.setDietary(cursor.getString(8));
            recipeList.add(recipeObject);
            cursor.moveToNext();
        }

        return  recipeList;
    }



}
