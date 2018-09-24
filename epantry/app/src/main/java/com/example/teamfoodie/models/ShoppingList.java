package com.example.teamfoodie.models;

/**
 */

public class ShoppingList {

    private int id;
    private String unit;
    private String materialName;
    private String materialValue;

    private boolean isChecked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialValue() {
        return materialValue;
    }

    public void setMaterialValue(String materialValue) {
        this.materialValue = materialValue;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id='" + id + '\'' +
                "unit='" + unit + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialValue='" + materialValue + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
