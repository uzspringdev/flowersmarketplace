package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.dto.CustomerDto;
import com.example.flowers_marketplace.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer/create")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.save(customerDto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value = "/customer/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping(value = "/customer/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.findById(id);

        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/customer/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.update(id, customerDto);

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(value = "/customer/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return customerService.delete(id) ? ResponseEntity.ok("Customer deleted") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }
}
