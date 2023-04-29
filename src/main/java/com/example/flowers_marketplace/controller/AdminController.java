package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.dto.AdminDto;
import com.example.flowers_marketplace.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody AdminDto adminDto) {
        Admin admin = adminService.save(adminDto);

        return ResponseEntity.ok(admin);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<Admin> adminList = adminService.findAll();
        return ResponseEntity.ok(adminList);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Admin admin = adminService.findById(id);

        return ResponseEntity.ok(admin);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody AdminDto adminDto) {
        Admin admin = adminService.update(id, adminDto);

        return ResponseEntity.ok(admin);
    }
}
