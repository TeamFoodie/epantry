package com.example.teamfoodie.models;


import java.util.List;

public class MainContentBean {

    private String title;
    private String introduction;
    private List<IngredientBean> ingredientBeanList;
    private List<String> procedureList;
    private String cookingTime;
    private String nutritionalCount;
    private String NumberOfPeople;
    private String tags;
    private String photoUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<IngredientBean> getIngredientBeanList() {
        return ingredientBeanList;
    }

    public void setIngredientBeanList(List<IngredientBean> ingredientBeanList) {
        this.ingredientBeanList = ingredientBeanList;
    }

    public List<String> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<String> procedureList) {
        this.procedureList = procedureList;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getNutritionalCount() {
        return nutritionalCount;
    }

    public void setNutritionalCount(String nutritionalCount) {
        this.nutritionalCount = nutritionalCount;
    }

    public String getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(String NumberOfPeople) {
        this.NumberOfPeople = NumberOfPeople;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "MainContentBean{" +
                "title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", ingredientBeanList=" + ingredientBeanList +
                ", procedureList=" + procedureList +
                ", cookingTime='" + cookingTime + '\'' +
                ", nutritionalCount='" + nutritionalCount + '\'' +
                ",NumberOfPeople='"+NumberOfPeople+'\'' +
                ", tags='" + tags + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
