package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Cart;
import com.example.flowers_marketplace.domain.CartDto;
import com.example.flowers_marketplace.repository.CartRepository;
import com.example.flowers_marketplace.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(CartDto cartDto) {
        return null;
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public Cart update(Long id, CartDto cartDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
