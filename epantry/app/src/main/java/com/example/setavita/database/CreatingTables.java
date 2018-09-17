package com.example.setavita.database;

public class CreatingTables {

    private static final String PantryIngredient = "CREATE TABLE INGREDIENT(" +
            "IngredientID NVARCHAR PRIMARY KEY," +
            "IngredientName NVARCHAR," +
            "TotalQuantity INTEGER,"  +
            "CurrentQuantity INTEGER,"  +
            "UnitMeasure NVARCHAR," +
            "Owner INTEGER);";

}
