package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Cart;
import com.example.flowers_marketplace.domain.CartDto;
import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.repository.CartRepository;
import com.example.flowers_marketplace.service.CartService;
import com.example.flowers_marketplace.service.CustomerService;
import com.example.flowers_marketplace.service.FlowerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final FlowerService flowerService;
    private final CustomerService customerService;

    public CartServiceImpl(CartRepository cartRepository, FlowerService flowerService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.flowerService = flowerService;
        this.customerService = customerService;
    }

    @Override
    public Cart save(CartDto cartDto) {
        List<Flower> flowerList = flowerService.findAllByIds(cartDto.getFlowers());
        Customer customer = customerService.findById(cartDto.getCustomerId());
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setFlowers(flowerList);

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
        if (optionalCart.isPresent()) {
            Cart editedCart = optionalCart.get();
            Customer customer = customerService.findById(id);
            List<Flower> flowerList = flowerService.findAllByIds(cartDto.getFlowers());
            List<Flower> editedCartFlowers = editedCart.getFlowers();
            if (customer != null && flowerList != null) {
                editedCartFlowers.addAll(flowerList);
                editedCart.setCustomer(customer);
                editedCart.setFlowers(editedCartFlowers);
            }

            return cartRepository.save(editedCart);
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
