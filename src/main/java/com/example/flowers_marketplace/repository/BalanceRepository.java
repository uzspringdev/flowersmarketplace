package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
