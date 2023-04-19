package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.dto.CustomerDto;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.model.JwtToken;
import com.example.flowers_marketplace.model.Login;
import com.example.flowers_marketplace.security.JwtService;
import com.example.flowers_marketplace.service.CustomerService;
import com.example.flowers_marketplace.service.MerchantService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class HomeController {
    private final JwtService jwtService;
    private final CustomerService customerService;
    private final MerchantService merchantService;

    public HomeController(JwtService jwtService, CustomerService customerService, MerchantService merchantService) {
        this.jwtService = jwtService;
        this.customerService = customerService;
        this.merchantService = merchantService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> signIn(@RequestBody Login login) {
        String token = jwtService.generateToken(login);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new JwtToken(token), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/customer/register")
    public ResponseEntity<?> signUpCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.save(customerDto);
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/merchant/register")
    public ResponseEntity<?> signUpMerchant(@RequestBody MerchantDto merchantDto) {
        Merchant merchant = merchantService.save(merchantDto);
        return ResponseEntity.ok(merchant);
    }


}
