package com.example.setavita.models;

import java.math.BigInteger;

public class PantryIngredients {
    private String ingredientID;
    private String ingredientName;
    private int totalQuantity;
    private int currentQuantity;
    private String unitMeasure;
    private int owner;

    public PantryIngredients(String id, String name, int total, int current, String unitMeasure, int owner){
        this.ingredientID = id;
        this.ingredientName = name;
        this.totalQuantity = total;
        this.currentQuantity = current;
        this.unitMeasure = unitMeasure;
        this.owner = owner;
    }

    public PantryIngredients(){

    }

    public void setIngredientID(String id){
        this.ingredientID = id;
    }

    public void setIngredientName(String name){
        this.ingredientName = name;
    }

    public void setTotalQuantity(int total){
        this.totalQuantity = total;
    }

    public void setCurrentQuantity(int current){
        this.currentQuantity = current;
    }

    public void setUnitMeasure(String measure){
        this.unitMeasure = measure;
    }

    public void setOwner(int owner){
        this.owner = owner;
    }

    public String getIngredientID(){
        return this.ingredientID;
    }

    public String getIngredientName(){
        return this.ingredientName;
    }

    public int getTotalQuantity(){
        return this.totalQuantity;
    }

    public int getCurrentQuantity(){
        return this.currentQuantity;
    }

    public void getUnitMeasu(int current){
        this.currentQuantity = current;
    }

    public int getOwner(){
        return this.owner;
    }

    public String getIngredientInformation(){
        return this.ingredientID+ " " +this.ingredientName + " " + this.totalQuantity + " " + this.owner;
    }

}
