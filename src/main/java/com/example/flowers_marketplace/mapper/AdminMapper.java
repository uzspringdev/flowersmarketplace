package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.AdminDto;
import com.example.flowers_marketplace.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

    AdminMapper getInstance = Mappers.getMapper(AdminMapper.class);

    Admin toEntity(AdminDto adminDto);

    AdminDto toDto(Admin admin);

    UserAccount toUserAccountEntity(UserAccountDto userAccountDto);

    UserAccountDto toUserAccountDto(UserAccount userAccount);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
