package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.StoreDto;

import java.util.List;

public interface StoreService {

    Store save(StoreDto storeDto);

    Store save(Store store);

    List<Store> saveAll(List<StoreDto> storeDtoList);

    List<Store> findAll();

    Store findById(Long id);

    Store update(Long id, StoreDto storeDto);

    Boolean delete(Long id);


}
