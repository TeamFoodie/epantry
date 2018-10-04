package com.example.teamfoodie.models;

import java.util.ArrayList;

public class Recipe {


    private int recipeID;
    private String recipeName;
    private String description;
    private int recipePhoto;
    private int calorieCount;
    private int cookingTime;
    private int author;
    private int numberOfPeople;
    private Dietary dietary;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Procedure> procedures;


    public Recipe() {

    }

    public Recipe(int id, String recipeName, int photo, String description, int calorieCount, int cookingTime, int author, int numberOfPeople, String foodGroup) {
        this.setRecipeID(id);
        this.setRecipeName(recipeName);
        this.setPhoto(photo);
        this.setDescription(description);
        this.setCalorieCount(calorieCount);
        this.setCookingTime(cookingTime);
        this.setAuthor(author);
        this.setNumberOfPeople(numberOfPeople);
        this.setDietary(foodGroup);
    }

    public Recipe(String recipeName, int photo, String description, int calorieCount, int cookingTime, int author, int numberOfPeople, String foodGroup, ArrayList<Ingredient> ingredients, ArrayList<Procedure> procedures) {
        this.setRecipeName(recipeName);
        this.setPhoto(photo);
        this.setDescription(description);
        this.setCalorieCount(calorieCount);
        this.setCookingTime(cookingTime);
        this.setAuthor(author);
        this.setNumberOfPeople(numberOfPeople);
        this.setDietary(foodGroup);
        this.setIngredients(ingredients);
        this.setProcedures(procedures);
    }


    public void setRecipeID(int id) {
        this.recipeID = id;
    }

    public int getRecipeID() {
        return this.recipeID;
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getPhoto() {
        return recipePhoto;
    }

    public void setPhoto(int recipePicName) {
        this.recipePhoto = recipePicName;
    }


    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getDietary() {
        return dietary.toString();
    }

    public void setDietary(String stringFoodGroup) {
        switch (stringFoodGroup){
            case "Vegetarian":
                this.dietary = Dietary.VEGETARIAN;
                break;
            case "Vegan":
                this.dietary = Dietary.VEGAN;
                break;
            case "Gluten Free":
                this.dietary = Dietary.GLUTEN_FREE;
                break;
            case "Dairy Free":
                this.dietary = Dietary.DAIRY_FREE;
                break;
            case "Comfort Food":
                this.dietary = Dietary.COMFORT;
                break;
        }

        this.dietary = dietary;
    }



    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(ArrayList<Procedure> procedures) {
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return this.recipeID + this.recipeName + this.description;
    }
}
