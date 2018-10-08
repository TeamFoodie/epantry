package com.example.teamfoodie.models;

public enum Dietary {
    HALAL("Halal"),KOSHER ("Kosher"),VEGETARIAN("Vegetarian"), VEGAN("Vegan"), HIGH_FIBER("High Fiber"),DAIRY_FREE("Dairy Free"), LOW_FAT ("Low Fat"),LOW_SALT ("Low Salt"),LOW_CARB ("Low Carb"),GLUTEN_FREE("Gluten Free"), COMFORT("Comfort Food");

    private String stringValue;

    private Dietary(String s){
        this.stringValue = s;
    }

    public String toString(){
        return stringValue;
    }
}
