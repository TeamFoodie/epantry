package com.example.teamfoodie.models;

public class Recipe {



    private String recipeName;

    // Image name (Without extension)
    private int recipeID;
    private int recipePhoto;
    private String description;
    private String URL;


    public Recipe() {

    }

    public Recipe(int id, int photo, String description, String url){
        this.setRecipeID(id);
        this.setPhoto(photo);
        this.setDescription(description);
        this.setURL(url);
    }

    public Recipe(String recipeName, int recipePhoto, String description, String URL) {
        this.recipeName = recipeName;
        this.recipePhoto = recipePhoto;
        this.description = description;
        this.URL = URL;
    }


    public void setRecipeID(int id){
        this.recipeID = id;
    }

    public int getRecipeID(){
        return this.recipeID;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getPhoto() {
        return recipePhoto;
    }

    public void setPhoto(int recipePicName) {
        this.recipePhoto = recipePicName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String url) {
        this.URL = url;
    }


    @Override
    public String toString() {
        return this.recipeName;
    }
}
