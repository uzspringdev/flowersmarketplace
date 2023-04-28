package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.dto.AdminDto;

import java.util.List;

public interface AdminService {
    Admin save(AdminDto adminDto);

    Admin save(Admin admin);

    Admin findById(Long id);

    List<Admin> findAll();

    Admin update(Long id, AdminDto adminDto);

    Boolean delete(Long id);
}
