package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
