package com.example.flowers_marketplace.dto;


import java.util.List;

public class CartDto {
    private Long id;
    private Long customerId;
    private List<Long> cartItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Long> cartItems) {
        this.cartItems = cartItems;
    }
}
