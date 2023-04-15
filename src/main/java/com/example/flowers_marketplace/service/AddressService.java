package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(AddressDto addressDto);

    Address save(Address address);

    Address findById(Long id);

    List<Address> findAll();

    Address update(Long id, AddressDto addressDto);

    Boolean delete(Long id);
}
