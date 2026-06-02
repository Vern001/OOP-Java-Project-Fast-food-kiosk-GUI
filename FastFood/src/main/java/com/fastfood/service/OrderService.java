package com.fastfood.service;
import com.fastfood.model.CartItem;
import com.fastfood.model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final List<CartItem> cartItems=new ArrayList<>();
    private OrderService() {

    }

    public static OrderService getInstance() {
        if (instance ==null) {
            instance=new OrderService();
        }
        return instance;
    }

    public void addItem(MenuItem item) {
        for (CartItem cartItem:cartItems) {
            if (cartItem.getMenuItem().getName().equals(item.getName())) {
                cartItem.incrementQuantity();
                return;
            }
        }
        cartItems.add(new CartItem(item, 1));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }


    public double calculateSubtotal() {
        double subtotal=0.0;
        for (CartItem item:cartItems) {
            subtotal+=item.getTotalPrice();
        }
        return subtotal;
    }


    public void removeItem(MenuItem item) {
        cartItems.removeIf(cartItem -> cartItem.getMenuItem().getName().equals(item.getName()));
    }
}
