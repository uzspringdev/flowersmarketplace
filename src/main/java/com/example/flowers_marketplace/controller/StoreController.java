package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.StoreDto;
import com.example.flowers_marketplace.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StoreDto storeDto) {
        Store store = storeService.save(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<Store> storeList = storeService.findAll();
        return ResponseEntity.ok(storeList);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Store store = storeService.findById(id);
        return ResponseEntity.ok(store);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody StoreDto storeDto) {
        Store store = storeService.update(id, storeDto);

        return ResponseEntity.ok(store);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return storeService.delete(id)
                ? ResponseEntity.ok("Store is deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
    }

}
