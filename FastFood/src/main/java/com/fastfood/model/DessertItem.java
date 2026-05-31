package com.fastfood.model;

public class DessertItem extends MenuItem {
    private String flavor;

    public DessertItem(String name, double price, String description, String flavor) {
        super(name,price,description);
        this.flavor=flavor;
    }

    public String getFlavor() {
       return flavor;
    }
}