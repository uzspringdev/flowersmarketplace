package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper getInstance = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

}
