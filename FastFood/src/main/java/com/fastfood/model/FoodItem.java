package com.fastfood.model;

public class FoodItem extends MenuItem {
    private boolean isVegetarian;

    public FoodItem(String name, double price, String description, boolean isVegetarian) {
        super(name, price, description);
        this.isVegetarian=isVegetarian;
    }

    public boolean isVegetarian() {

        return isVegetarian;
    }
}