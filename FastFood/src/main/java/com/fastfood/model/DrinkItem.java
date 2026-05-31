package com.fastfood.model;

public class DrinkItem extends MenuItem {
    private String size;

    public DrinkItem(String name, double price, String description, String size) {
        super(name, price, description);
        this.size=size;
    }

    public String getSize() {
        return size;
    }
}