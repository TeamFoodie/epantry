package com.example.teamfoodie.models;

public enum FoodGroup {
    SPICES("Spices"), POULTRY("Poultry"), STAPLE("Basic Neccesities"), VEGETABLES("Vegetables"), MEATS("Meats"), SAUCES("Sauces"), OILS("Oils"), ;

    private String stringValue;

    private FoodGroup(String s){
        this.stringValue = s;
    }

    @Override
    public String toString(){
        return  this.stringValue;
    }

}
