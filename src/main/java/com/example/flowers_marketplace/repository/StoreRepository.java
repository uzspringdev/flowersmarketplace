package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
