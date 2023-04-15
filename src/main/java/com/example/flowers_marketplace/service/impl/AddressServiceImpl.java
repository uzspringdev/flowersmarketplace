package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.mapper.AddressMapper;
import com.example.flowers_marketplace.repository.AddressRepository;
import com.example.flowers_marketplace.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper = AddressMapper.getInstance;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        return addressRepository.save(address);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address update(Long id, AddressDto addressDto) {
        if (addressRepository.existsById(id)) {
            Address address = addressMapper.toEntity(addressDto);
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
