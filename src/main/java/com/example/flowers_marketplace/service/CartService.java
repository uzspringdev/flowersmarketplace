package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Cart;
import com.example.flowers_marketplace.domain.CartItem;
import com.example.flowers_marketplace.dto.CartDto;
import com.example.flowers_marketplace.dto.CartItemDto;

import java.util.List;

public interface CartService {

    Cart save(CartDto cartDto);

    Cart save(Cart cart);

    List<Cart> findAll();

    Cart findById(Long id);

    Cart findByCustomerId(Long customerId);

    Cart update(Long id, CartDto cartDto);

    Boolean delete(Long id);

    Cart addCartItem(CartItemDto cartItemDto);
}
