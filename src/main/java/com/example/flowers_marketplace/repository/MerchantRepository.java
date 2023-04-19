package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Merchant findByUsername(String username);
}
