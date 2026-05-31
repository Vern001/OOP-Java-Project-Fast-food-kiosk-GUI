package com.fastfood.model;

public class ComboItem extends MenuItem {
    private MenuItem[] itemsInCombo;

    public ComboItem(String name, double price, String description, MenuItem[] itemsInCombo) {
        super(name, price, description);
        this.itemsInCombo=itemsInCombo;
    }

    public MenuItem[] getItemsInCombo() {
        return itemsInCombo;
    }
}