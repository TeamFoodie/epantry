package com.example.teamfoodie.models;

public class Ingredient {


    private int recipeID=0;
    private String name="";
    private double measurement=0.0;
    private String unitCount="";

    public Ingredient(){

    }
    public Ingredient(String name, double measurement, String unitCount){
        this.setName(name);
        this.setMeasurement(measurement);
        this.setUnitCount(unitCount);
    }
    public Ingredient(int recipeID, String name, double measurement, String unitCount){
        this.setRecipeID(recipeID);
        this.setName(name);
        this.setMeasurement(measurement);
        this.setUnitCount(unitCount);
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public String getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(String unitCount) {
        this.unitCount = unitCount;
    }

    public void setInfo(String name, double measurement, String unitCount){
        this.setName(name);
        this.setMeasurement(measurement);
        this.setUnitCount(unitCount);
    }

    public String toString(){
        return this.recipeID+"  "+this.getMeasurement() + this.getUnitCount() + " of " + this.getName();
    }
}
