package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.*;
import com.example.flowers_marketplace.dto.CartItemDto;
import com.example.flowers_marketplace.repository.CartRepository;
import com.example.flowers_marketplace.service.CartService;
import com.example.flowers_marketplace.service.CustomerService;
import com.example.flowers_marketplace.service.CartItemService;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.flowers_marketplace.dto.CartDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CustomerService customerService;
    private final UserDetailsService userDetailsService;
    private final UserAccountService userAccountService;

    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, CustomerService customerService, UserDetailsService userDetailsService, UserAccountService userAccountService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.customerService = customerService;
        this.userDetailsService = userDetailsService;
        this.userAccountService = userAccountService;
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
        Customer currentUser = customerService.getCurrentUser();
        return cartRepository.findAllByCustomerId(currentUser.getId());
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart findByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId).orElse(null);
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

    @Override
    public Cart addCartItem(CartItemDto cartItemDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
            Long userAccountId = userAccount.getId();
            Customer customer = customerService.findByUserAccountId(userAccountId);
            Optional<Cart> optionalCart = cartRepository.findByCustomerId(customer.getId());
            Cart cart = optionalCart.orElseGet(Cart::new);
            List<CartItem> cartItems;
            if (cart.getCartItems() != null)
                cartItems = cart.getCartItems();
            else cartItems= new ArrayList<>();
            CartItem cartItem = cartItemService.save(cartItemDto);
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
            cart.setCustomer(customer);
            return cartRepository.save(cart);
        }

        return null;

    }
}
