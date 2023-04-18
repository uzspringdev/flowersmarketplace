package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Store;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.StoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StoreMapper {

    StoreMapper getInstance = Mappers.getMapper(StoreMapper.class);

    Store toEntity(StoreDto storeDto);

    StoreDto toDto(Store store);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

    List<Store> toListEntity(List<StoreDto> storeDtoList);

    default Store updateStoreFromDto(StoreDto storeDto, Store store) {
        if (storeDto == null) {
            return null;
        }
        if (storeDto.getId() != null)
            store.setId(storeDto.getId());
        if (storeDto.getName() != null)
            store.setName(storeDto.getName());
        if (storeDto.getAddress() != null)
            store.setAddress(toAddressEntity(storeDto.getAddress()));
        store.setCreatedAt(storeDto.getCreatedAt());
        store.setUpdatedAt(storeDto.getUpdatedAt());

        return store;
    }

}
