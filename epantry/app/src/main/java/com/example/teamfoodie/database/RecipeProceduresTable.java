package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.Procedure;

import java.util.ArrayList;
import java.util.List;

public class RecipeProceduresTable {

    //columns for Ingredient Table
    public static final String RECIPE_ID = "RecipeID";
    public static final String PROCEDURE = "Procedure";

    //Returns create string for creating Pantry ingredient table.
    public String createRecipeProcedureTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + RECIPE_ID + " INTEGER NOT NULL," +
                PROCEDURE + " NVARCHAR);";
        return createTable;
    }


    public ContentValues getProcedure(int id, String procedure) {
        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, id);
        values.put(PROCEDURE, procedure);
        return values;
    }


    public List<Procedure> loadAllRecipeProcedures(Cursor cursor) {
        List<Procedure> procedureList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Procedure procedure = new Procedure(cursor.getInt(0),cursor.getString(1));
            procedureList.add(procedure);
            System.out.println("passed through load all recipe procedure");
            cursor.moveToNext();
        }

        return procedureList;
    }
}
