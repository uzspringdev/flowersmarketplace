package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Cart;
import com.example.flowers_marketplace.domain.CartDto;
import com.example.flowers_marketplace.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CartDto cartDto) {
        Cart cart = cartService.save(cartDto);

        return ResponseEntity.ok(cart);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<Cart> cartList = cartService.findAll();

        return ResponseEntity.ok(cartList);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Cart cart = cartService.findById(id);

        return ResponseEntity.ok(cart);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody CartDto cartDto) {
        Cart cart = cartService.update(id, cartDto);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return cartService.delete(id)
                ? ResponseEntity.status(HttpStatus.OK).body("Cart deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
    }
}
