package com.example.flowers_marketplace.controller;


import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.save(addressDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Address> addressList = addressService.findAll();
        return ResponseEntity.ok(addressList);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Address address = addressService.findById(id);
        return address != null ? ResponseEntity.ok(address) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody AddressDto addressDto) {

        return ResponseEntity.ok(addressService.update(id, addressDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")Long id){
        return addressService.delete(id)? ResponseEntity.ok("Address is deleted"):ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
    }
}
