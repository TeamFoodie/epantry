package com.example.teamfoodie.models;

/**
 * Class for creating a pantry ingredient object
 */
public class PantryIngredient {
    private String ingredientID;
    private String ingredientName;
    private int totalQuantity;
    private int currentQuantity;
    private String unitMeasure;
    private int owner;

    /**
     * Default Pantry ingredient - allows object to be instantiated without any parameters
     */
    public PantryIngredient(){

    }

    /**
     * Main constructor with all variables instantiated through parameters
     *
     * @param id
     * @param name
     * @param total
     * @param current
     * @param unitMeasure
     * @param owner
     */
    public PantryIngredient(String id, String name, int total, int current, String unitMeasure, int owner){
        this.ingredientID = id;
        this.ingredientName = name;
        this.totalQuantity = total;
        this.currentQuantity = current;
        this.unitMeasure = unitMeasure;
        this.owner = owner;
    }


    //All setters and getters for variables
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

    public String getUnitMeasure(){
        return this.unitMeasure;
    }

    public int getOwner(){
        return this.owner;
    }

    public String getIngredientInformation(){
        return this.ingredientID+ " " +this.ingredientName + " " + this.totalQuantity + " " + this.owner;
    }

}
