package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.*;
import com.example.flowers_marketplace.repository.CartRepository;
import com.example.flowers_marketplace.service.CartService;
import com.example.flowers_marketplace.service.CustomerService;
import com.example.flowers_marketplace.service.CartItemService;
import org.springframework.stereotype.Service;
import com.example.flowers_marketplace.dto.CartDto;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CustomerService customerService;

    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.customerService = customerService;
    }


    @Override
    public Cart save(CartDto cartDto) {
        Customer customer = customerService.findById(cartDto.getCustomerId());
        List<CartItem> cartItemList = cartItemService.findAllByIds(cartDto.getCartItems());
        Cart cart = new Cart();
        if (customer != null && cartItemList != null) {
            cart.setCustomer(customer);
            cart.setCartItems(cartItemList);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart update(Long id, CartDto cartDto) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Customer customer = customerService.findById(cartDto.getCustomerId());
        List<CartItem> cartItemList = cartItemService.findAllByIds(cartDto.getCartItems());

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            if (customer != null) {
                cart.setCustomer(customer);
            }
            if (cartItemList != null) {
                List<CartItem> cartItems = cart.getCartItems();
                cartItems.addAll(cartItemList);
                cart.setCartItems(cartItems);
            }

            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
