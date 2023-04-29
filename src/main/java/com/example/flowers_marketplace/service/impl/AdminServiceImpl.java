package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.AdminDto;
import com.example.flowers_marketplace.mapper.AdminMapper;
import com.example.flowers_marketplace.repository.AdminRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.AdminService;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Address address = addressService.save(admin.getAddress());
        UserAccount userAccount = userAccountService.save(admin.getUserAccount());
        admin.setAddress(address);
        admin.setUserAccount(userAccount);

        return adminRepository.save(admin);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin update(Long id, AdminDto adminDto) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = adminMapper.updateFromDto(adminDto, optionalAdmin.get());
            return adminRepository.save(admin);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
