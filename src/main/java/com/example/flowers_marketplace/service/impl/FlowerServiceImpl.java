package com.example.flowers_marketplace.service.impl;


import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.FlowerDto;
import com.example.flowers_marketplace.mapper.FlowerMapper;
import com.example.flowers_marketplace.repository.FlowerRepository;
import com.example.flowers_marketplace.service.FlowerService;
import com.example.flowers_marketplace.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;
    private final StoreService storeService;
    private final FlowerMapper flowerMapper = FlowerMapper.getInstance;

    public FlowerServiceImpl(FlowerRepository flowerRepository, StoreService storeService) {
        this.flowerRepository = flowerRepository;
        this.storeService = storeService;
    }

    @Override
    public Flower save(FlowerDto flowerDto) {
        Flower flower = flowerMapper.toEntity(flowerDto);
        Store store = storeService.findById(flowerDto.getStoreId());
        flower.setStore(store);
        return flowerRepository.save(flower);
    }

    @Override
    public Flower save(Flower flower) {
        return flowerRepository.save(flower);
    }

    @Override
    public List<Flower> saveAll(List<Long> ids) {
        return null;
    }

    @Override
    public List<Flower> findAllByIds(List<Long> listIds) {
        return flowerRepository.findAllById(listIds);
    }

    @Override
    public Flower findById(Long id) {
        return flowerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flower> findAll() {
        return flowerRepository.findAll();
    }

    @Override
    public Flower update(Long id, FlowerDto flowerDto) {
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        if (optionalFlower.isPresent()) {
            Flower flower = flowerMapper.updateFlowerFromDto(flowerDto, optionalFlower.get());
            Store store = getStore(flower, flowerDto);
            flower.setStore(store);
            return flowerRepository.save(flower);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (flowerRepository.existsById(id)) {
            flowerRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private Store getStore(Flower flower, FlowerDto flowerDto) {
        if (flowerDto.getStoreId() != null) {
            return storeService.findById(flowerDto.getStoreId());
        } else {
            return flower.getStore();
        }
    }
}
