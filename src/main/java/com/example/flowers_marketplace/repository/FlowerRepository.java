package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
}
