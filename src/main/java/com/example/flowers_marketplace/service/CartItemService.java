package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.CartItem;
import com.example.flowers_marketplace.dto.CartItemDto;

import java.util.List;

public interface CartItemService {

    CartItem save(CartItemDto cartItemDto);

    CartItem save(CartItem cartItem);

    List<CartItem> findAll();

    List<CartItem> findAllByIds(List<Long> ids);

    CartItem findById(Long id);

    CartItem update(Long id, CartItemDto cartItemDto);

    Boolean delete(Long id);
}
