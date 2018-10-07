package com.example.teamfoodie.models;

import java.text.BreakIterator;

/**
 * Class for creating a pantry ingredient object
 */
public class PantryIngredient {
    private String ingredientID;
    private String ingredientName;
    private int totalQuantity;
    private int currentQuantity;
    private String unitMeasure;
    private FoodGroup foodGroup;
    private int owner;
    private boolean priority;

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
    public PantryIngredient(String id, String name, int total, int current, String unitMeasure, String foodGroup, int owner, boolean priority){
        this.setIngredientID(id);
        this.setIngredientName(name);
        this.setTotalQuantity(total);
        this.setCurrentQuantity(current);
        this.setUnitMeasure(unitMeasure);
        this.setFoodGroup(foodGroup);
        this.setOwner(owner);
        this.setPriority(priority);
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

    public void setPriority(boolean priority){
        this.priority = priority;
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


    public String getFoodGroup() {
        return foodGroup.toString();
    }

    public void setFoodGroup(String food) {
        switch (food){
            case "Spices":
                this.foodGroup = FoodGroup.SPICES;
                break;
            case "Poultry":
                this.foodGroup = FoodGroup.POULTRY;
                break;
            case "Basic Neccesities":
                this.foodGroup = FoodGroup.STAPLE;
                break;
            case "Vegetables":
                this.foodGroup = FoodGroup.VEGETABLES;
                break;
            case "Meats":
                this.foodGroup = FoodGroup.MEATS;
                break;
            case "Sauces":
                this.foodGroup = FoodGroup.SAUCES;
                break;
            case "Oils":
                this.foodGroup = FoodGroup.OILS;
                break;
        }

    }



    public int getOwner(){
        return this.owner;
    }

    public boolean getPriority(){
        return this.priority;
    }

    public String getIngredientInformation(){
        return this.ingredientID+ " " +this.ingredientName + " " + this.totalQuantity + " " + this.owner;
    }

}
