package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountMapper {

    UserAccountMapper getInstance = Mappers.getMapper(UserAccountMapper.class);

    UserAccount toEntity(UserAccountDto userAccountDto);

    UserAccountDto toDto(UserAccount userAccount);
}
