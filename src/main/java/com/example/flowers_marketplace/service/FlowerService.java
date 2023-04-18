package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.dto.FlowerDto;

import java.util.List;

public interface FlowerService {

    Flower save(FlowerDto flowerDto);

    Flower save(Flower flower);

    Flower findById(Long id);

    List<Flower> findAll();

    Flower update(Long id, FlowerDto flowerDto);

    Boolean delete(Long id);
}
