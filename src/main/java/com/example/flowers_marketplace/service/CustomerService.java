package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer save(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    Customer update(Long id, CustomerDto customerDto);

    Boolean delete(Long id);
}
