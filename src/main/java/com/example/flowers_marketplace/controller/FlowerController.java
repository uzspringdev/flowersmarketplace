package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.dto.FlowerDto;
import com.example.flowers_marketplace.service.FlowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @PostMapping(value = "/flower/create")
    public ResponseEntity<?> create(@RequestBody FlowerDto flowerDto) {
        Flower flower = flowerService.save(flowerDto);
        return ResponseEntity.ok(flower);
    }

    @GetMapping(value = "/flowers/getAll")
    public ResponseEntity<?> getAll() {
        List<Flower> flowerList = flowerService.findAll();
        return ResponseEntity.ok(flowerList);
    }

    @GetMapping(value = "/flowers/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Flower flower = flowerService.findById(id);
        return ResponseEntity.ok(flower);
    }

    @PutMapping(value = "/flower/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody FlowerDto flowerDto) {
        Flower flower = flowerService.update(id, flowerDto);
        return ResponseEntity.ok(flower);
    }

    @DeleteMapping(value = "/flower/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return flowerService.delete(id)
                ? ResponseEntity.ok("Flower deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flower not Found");
    }
}
