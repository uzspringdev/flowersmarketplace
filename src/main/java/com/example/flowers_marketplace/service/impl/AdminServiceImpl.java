package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.dto.AdminDto;
import com.example.flowers_marketplace.mapper.AdminMapper;
import com.example.flowers_marketplace.repository.AdminRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.AdminService;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserAccountService userAccountService;
    private final AddressService addressService;
    private final AdminMapper adminMapper = AdminMapper.getInstance;


    public AdminServiceImpl(AdminRepository adminRepository, AddressService addressService, UserAccountService userAccountService) {
        this.adminRepository = adminRepository;
        this.addressService = addressService;
        this.userAccountService = userAccountService;
    }


    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = adminMapper.toEntity(adminDto);
        return null;
    }

    @Override
    public Admin save(Admin admin) {
        return null;
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Admin update(Long id, AdminDto adminDto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
