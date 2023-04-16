package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/merchant/getAll")
    public ResponseEntity<?> getAll() {
        List<Merchant> merchantList = merchantService.findAll();
        return ResponseEntity.ok(merchantList);
    }

    @GetMapping(value = "/merchant/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Merchant merchant = merchantService.findById(id);

        return ResponseEntity.ok(merchant);
    }

    @PutMapping(value = "/merchant/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody MerchantDto merchantDto) {
        Merchant merchant = merchantService.update(id, merchantDto);
        return ResponseEntity.ok(merchant);
    }

    @DeleteMapping(value = "/merchant/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return merchantService.delete(id)
                ? ResponseEntity.status(HttpStatus.OK).body("Merchant deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Merchant not found");
    }

}