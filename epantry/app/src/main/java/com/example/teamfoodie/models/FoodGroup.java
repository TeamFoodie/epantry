package com.example.teamfoodie.models;

public enum FoodGroup {
    SPICES(1, "Spices"), POULTRY(2, "Poultry"), STAPLE(3, "Staple"), VEGETABLES(4, "Vegetables"), MEATS(5, "Meats"), SAUCES(6, "Sauces"), OILS(7, "Oils"), ;

    private String stringValue;
    private int intValue;

    private FoodGroup(int i, String s){
        this.intValue = i;
        this.stringValue = s;
    }

    @Override
    public String toString(){
        return  this.stringValue;
    }

    public int integerValue(){
        return this.intValue;
    }

}
