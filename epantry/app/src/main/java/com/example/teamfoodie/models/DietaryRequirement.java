package com.example.teamfoodie.models;

public class DietaryRequirement {

    private int userID;
    private Dietary dietary;


    public DietaryRequirement(int id, Dietary dietary){
        this.setUserID(id);
        this.setDietary(dietary);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDietary() {
        return dietary.toString();
    }

    public void setDietary(Dietary dietary) {
        this.dietary = dietary;
    }

}
