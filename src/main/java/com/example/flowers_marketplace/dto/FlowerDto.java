package com.example.flowers_marketplace.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class FlowerDto {
    private String name;

    private Double price;

    private Float discount;

    private String type;

    private Long storeId;

    private Long count;

    private String descriptionUz;

    private String descriptionRu;

    private String descriptionEn;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
