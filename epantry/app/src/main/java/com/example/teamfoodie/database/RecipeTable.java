package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.Recipe;
import com.example.teamfoodie.models.User;

public class RecipeTable {

    private String username;
    private String password;

    //columns for User Table
    private static final String RECIPE_ID = "RecipeID";
    private static final String RECIPE_NAME = "Name";
    private static final String RECIPE_DESCRIPTION = "Description";
    private static final String RECIPE_PHOTO = "Photo";
    private static final String RECIPE_URL = "URL";
//    private static final String[] USER_COLUMNS = {RECIPE_ID, RECIPE_NAME, RECIPE_DESCRIPTION, RECIPE_URL};
//
//
//    private DatabaseHandler dbHandler;
//    private User currentUser;
//    private Object object;

    public String createRecipeTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RECIPE_NAME + " TEXT, "+
                RECIPE_DESCRIPTION + " TEXT, "+
                RECIPE_PHOTO + " INTEGER," +
                RECIPE_URL + " TEXT );";

        return createTable;
    }

    public ContentValues addNewRecipe(Recipe recipeObject) {
        ContentValues values = new ContentValues();
        values.put(RECIPE_NAME, recipeObject.getRecipeName());
        values.put(RECIPE_PHOTO, recipeObject.getPhoto());
        values.put(RECIPE_DESCRIPTION, recipeObject.getDescription());
        values.put(RECIPE_URL, recipeObject.getURL());
        return values;
    }

    public Recipe findRecipe(Cursor cursor) {
        Recipe existingRecipe = new Recipe();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            existingRecipe.setRecipeID(cursor.getInt(0));
            existingRecipe.setRecipeName(cursor.getString(1));
            existingRecipe.setPhoto(cursor.getInt(2));
            existingRecipe.setDescription(cursor.getString(3));
            existingRecipe.setURL(cursor.getString(4));
            cursor.close();
        } else {
            return null;
        }

        return existingRecipe;
    }

//
//    public User checkLogin(String user, String pass, DatabaseHandler db){
//
//        this.dbHandler = db;
//        dbHandler.setUser(user, pass);
//
//        object = dbHandler.findHandle(user, "User");
//        if(object == null){
//            System.out.println("check login was  null");
//            return null;
//        }else{
//            currentUser = (User) object;
//
//        }
//        return currentUser;
//    }


}
