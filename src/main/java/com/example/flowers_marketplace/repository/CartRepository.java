package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerId(Long customerId);

    List<Cart> findAllByCustomerId(Long id);
}
