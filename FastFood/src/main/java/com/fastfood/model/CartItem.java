package com.fastfood.model;

public class CartItem {
    private final MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem=menuItem;
        this.quantity=quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public MenuItem getMenuItem() {

        return menuItem;
    }

    public int getQuantity() {

        return quantity;
    }

    public void incrementQuantity() {

        this.quantity++;
    }
    public void decrementQuantity(){
        if(this.quantity>1){
            this.quantity--;
        }
    }

    public double getTotalPrice() {

        return menuItem.getPrice()*quantity;
    }
}