package com.example.flowers_marketplace.domain;

import com.example.flowers_marketplace.dto.FlowerDto;

import java.util.List;

public class CartDto {
    private Integer id;
    private Integer customerId;
    private List<FlowerDto> flowers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<FlowerDto> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<FlowerDto> flowers) {
        this.flowers = flowers;
    }
}
