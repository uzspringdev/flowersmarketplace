package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Flower;
import com.example.flowers_marketplace.dto.FlowerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlowerMapper {

    FlowerMapper getInstance = Mappers.getMapper(FlowerMapper.class);

    Flower toEntity(FlowerDto flowerDto);

    FlowerDto toDto(Flower flower);

    default Flower updateFlowerFromDto(FlowerDto flowerDto, Flower flower) {
        if (flowerDto == null) {
            return flower;
        }

        if (flowerDto.getName() != null)
            flower.setName(flowerDto.getName());
        if (flowerDto.getPrice() != null)
            flower.setPrice(flowerDto.getPrice());
        if (flowerDto.getDiscount() != null)
            flower.setDiscount(flowerDto.getDiscount());
        if (flowerDto.getType() != null)
            flower.setType(flowerDto.getType());
        if (flowerDto.getStock() != null)
            flower.setStock(flowerDto.getStock());
        if (flowerDto.getDescriptionUz() != null)
            flower.setDescriptionUz(flowerDto.getDescriptionUz());
        if (flowerDto.getDescriptionRu() != null)
            flower.setDescriptionRu(flowerDto.getDescriptionRu());
        if (flowerDto.getDescriptionEn() != null)
            flower.setDescriptionEn(flowerDto.getDescriptionEn());
        if (flowerDto.getCreatedAt() != null)
            flower.setCreatedAt(flowerDto.getCreatedAt());
        if (flowerDto.getUpdatedAt() != null)
            flower.setUpdatedAt(flowerDto.getUpdatedAt());

        return flower;
    }
}
