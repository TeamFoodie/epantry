package com.example.teamfoodie.models;

public class Tag {


    private int recipeID;
    private int TagID;
    private String name;


    public Tag(int TagID, String name){
        this.setTagID(TagID);
        this.setName(name);

    }
    public Tag(int recipeID,int TagID,String name){
        this.setRecipeID(recipeID);
        this.setTagID(TagID);
        this.setName(name);
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getTagID() {
        return TagID;
    }

    public void setTagID(int TagID) {
        this.TagID = TagID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String toString(){
        return this.getRecipeID() + this.getTagID() + " of " + this.getName();
    }
}
