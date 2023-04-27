package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
