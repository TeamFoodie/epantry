//package com.example.teamfoodie.database;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//
//import com.example.teamfoodie.models.Ingredient;
//import com.example.teamfoodie.models.Photo;
//import com.example.teamfoodie.models.Procedure;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecipePhotoTable {
//    public static final String RECIPE_ID = "RecipeID";
//    public static final String PHOTOURL = "PhotoUrl";
//
//    public String createPhotoTable(String TABLE_NAME) {
//        String createTable = "CREATE TABLE " + TABLE_NAME +
//                "(" + RECIPE_ID + " INTEGER NOT NULL," +
//                PHOTOURL + " NVARCHAR);";
//        return createTable;
//    }
//
//    public ContentValues getPhotoContents(int id,String url) {
//        ContentValues values = new ContentValues();
//        values.put(RECIPE_ID, id);
//        values.put(PHOTOURL, url);
//        return values;
//    }
//
//    public Photo loadPhotoUrl(Cursor cursor){
//
//        cursor.moveToFirst();
//        Photo photo=new Photo(cursor.getInt(0),cursor.getString(1));
//
//        return photo;
//    }
//}
