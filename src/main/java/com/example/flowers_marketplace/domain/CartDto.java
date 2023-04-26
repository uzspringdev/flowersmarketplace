package com.example.flowers_marketplace.domain;


import java.util.List;

public class CartDto {
    private Long id;
    private Long customerId;
    private List<Long> flowers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Long> flowers) {
        this.flowers = flowers;
    }
}
