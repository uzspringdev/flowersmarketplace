package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.StoreDto;
import com.example.flowers_marketplace.mapper.StoreMapper;
import com.example.flowers_marketplace.repository.StoreRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.MerchantService;
import com.example.flowers_marketplace.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final MerchantService merchantService;
    private final AddressService addressService;
    private final StoreMapper storeMapper = StoreMapper.getInstance;

    public StoreServiceImpl(StoreRepository storeRepository, MerchantService merchantService, AddressService addressService) {
        this.storeRepository = storeRepository;
        this.merchantService = merchantService;
        this.addressService = addressService;
    }

    @Override
    public Store save(StoreDto storeDto) {
        Store store = storeMapper.toEntity(storeDto);
        Address address = addressService.save(store.getAddress());
        Long merchantId = storeDto.getMerchantId();
        Merchant merchant = merchantService.findById(merchantId);
        store.setMerchant(merchant);
        store.setAddress(address);
        return storeRepository.save(store);
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> saveAll(List<StoreDto> storeDtoList) {
        List<Store> storeList = storeMapper.toListEntity(storeDtoList);
        return storeRepository.saveAll(storeList);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id) {

        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public Store update(Long id, StoreDto storeDto) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if (optionalStore.isPresent()) {
            Store store = storeMapper.updateStoreFromDto(storeDto, optionalStore.get());
            Merchant merchant = getMerchant(storeDto, store);
            store.setMerchant(merchant);
            return storeRepository.save(store);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Merchant getMerchant(StoreDto storeDto, Store store) {
        if (storeDto.getId() != null) {
            return merchantService.findById(storeDto.getId());
        } else {
            return store.getMerchant();
        }
    }
}
