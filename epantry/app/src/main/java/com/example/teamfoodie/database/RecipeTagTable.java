package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class RecipeTagTable {

    //columns for Tag Table
    public static final String RECIPE_ID = "RecipeID";
    public static final String TAG_NAME = "TagName";


    public String createRecipeTagTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + RECIPE_ID + " INTEGER NOT NULL," +
                TAG_NAME + " NVARCHAR);";
        return createTable;
    }

    public ContentValues getTagContents(Tag tag) {
        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, tag.getRecipeID());
        values.put(TAG_NAME, tag.getName());
        return values;
    }


    public List<Tag> loadAllTags(Cursor cursor) {
        List<Tag> tagList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tag tag = new Tag(cursor.getInt(0),cursor.getString(2));
            tagList.add(tag);
            System.out.println("passed through load all recipe Tags");
            System.out.println(tag.toString());
            cursor.moveToNext();
        }

        return tagList;
    }

}
