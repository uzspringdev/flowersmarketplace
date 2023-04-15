package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping(value = "/merchant/create")
    public ResponseEntity<?> create(@RequestBody MerchantDto merchantDto) {
        Merchant merchant = merchantService.save(merchantDto);
        return ResponseEntity.ok(merchant);

    }

    @PutMapping(value = "/merchant/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody MerchantDto merchantDto) {
        Merchant merchant = merchantService.update(id, merchantDto);
        return ResponseEntity.ok(merchant);
    }

}
