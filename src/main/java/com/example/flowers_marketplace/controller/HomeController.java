package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.dto.CustomerDto;
import com.example.flowers_marketplace.model.JwtToken;
import com.example.flowers_marketplace.model.Login;
import com.example.flowers_marketplace.security.JwtService;
import com.example.flowers_marketplace.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class HomeController {
    private final JwtService jwtService;
    private final CustomerService customerService;

    public HomeController(JwtService jwtService, CustomerService customerService) {
        this.jwtService = jwtService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> signIn(@RequestBody Login login) {
        String token = jwtService.generateToken(login);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new JwtToken(token), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/customer/register")
    public ResponseEntity<?> signUp(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.save(customerDto);
        return ResponseEntity.ok(customer);
    }


}
