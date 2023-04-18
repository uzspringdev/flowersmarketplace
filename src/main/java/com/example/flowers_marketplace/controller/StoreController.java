package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.StoreDto;
import com.example.flowers_marketplace.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> create(@RequestBody StoreDto storeDto) {
        Store store = storeService.save(storeDto);
        return ResponseEntity.ok(store);
    }

    @GetMapping(value = "/store/getAll")
    public ResponseEntity<?> getAll() {
        List<Store> storeList = storeService.findAll();
        return ResponseEntity.ok(storeList);
    }

    @GetMapping(value = "/store/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Store store = storeService.findById(id);
        return ResponseEntity.ok(store);
    }

    @PutMapping(value = "/store/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody StoreDto storeDto) {
        Store store = storeService.update(id, storeDto);

        return ResponseEntity.ok(store);
    }

    @DeleteMapping(value = "/store/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return storeService.delete(id)
                ? ResponseEntity.ok("Store is deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
    }

}
