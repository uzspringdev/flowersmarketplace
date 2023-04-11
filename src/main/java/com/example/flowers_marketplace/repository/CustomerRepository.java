package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
