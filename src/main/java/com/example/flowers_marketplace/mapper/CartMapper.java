package com.example.flowers_marketplace.mapper;


import com.example.flowers_marketplace.domain.Cart;
import com.example.flowers_marketplace.domain.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper getInstance = Mappers.getMapper(CartMapper.class);

    Cart toEntity(CartDto cartDto);

    CartDto toDto(Cart cart);
}
