package com.example.teamfoodie.models;

public enum Dietary {
    VEGETARIAN("Vegetarian"), VEGAN("Vegan"), GLUTEN_FREE("Gluten Free"), DAIRY_FREE("Dairy Free"), COMFORT("Comfort Food");

    private String stringValue;

    private Dietary(String s){
        this.stringValue = s;
    }

    public String toString(){
        return stringValue;
    }
}
