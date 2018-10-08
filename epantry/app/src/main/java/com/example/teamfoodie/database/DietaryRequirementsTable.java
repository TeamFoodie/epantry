package com.example.teamfoodie.database;

import android.content.ContentValues;

import com.example.teamfoodie.models.DietaryRequirement;

public class DietaryRequirementsTable {

    public static final String USER_ID = "UserID";
    public static final String DIETARY = "DietaryRequirement";

    public String createDietaryTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + USER_ID + " INTEGER NOT NULL," +
                DIETARY + " NVARCHAR);";
        return createTable;
    }

    public ContentValues addNewDietaryRequirement(DietaryRequirement dietary){
        ContentValues values = new ContentValues();
        values.put(USER_ID, dietary.getUserID());
        values.put(DIETARY, dietary.getDietary());
        return values;
    }

}
