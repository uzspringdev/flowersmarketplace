package com.example.flowers_marketplace.repository;

import com.example.flowers_marketplace.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
