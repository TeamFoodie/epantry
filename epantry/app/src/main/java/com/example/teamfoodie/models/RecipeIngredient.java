package com.example.teamfoodie.models;
/*
bean for ingredient
 */

public class RecipeIngredient {
    private String ingredient;
    private String quantity;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "ingredient='" + ingredient + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
