package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.CartItem;
import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.dto.CartItemDto;
import com.example.flowers_marketplace.repository.CartItemRepository;
import com.example.flowers_marketplace.service.CartItemService;
import com.example.flowers_marketplace.service.FlowerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    private final FlowerService flowerService;


    public CartItemServiceImpl(CartItemRepository cartItemRepository, FlowerService flowerService) {
        this.cartItemRepository = cartItemRepository;
        this.flowerService = flowerService;
    }

    @Override
    public CartItem save(CartItemDto cartItemDto) {
        Flower flower = flowerService.findById(cartItemDto.getFlowerId());
        CartItem cartItem = new CartItem();
        try {
            cartItem.setFlower(flower);
            cartItem.setQuantity(cartItemDto.getQuantity());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findAll() {

        return cartItemRepository.findAll();
    }

    @Override
    public List<CartItem> findAllByIds(List<Long> ids) {
        return cartItemRepository.findAllById(ids);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem update(Long id, CartItemDto cartItemDto) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        Flower flower = flowerService.findById(cartItemDto.getFlowerId());
        if (optionalCartItem.isPresent()) {
            CartItem editedCartItem = optionalCartItem.get();
            if (flower != null) {
                editedCartItem.setFlower(flower);
            }
            editedCartItem.setQuantity(cartItemDto.getQuantity());

            return cartItemRepository.save(editedCartItem);
        }

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
