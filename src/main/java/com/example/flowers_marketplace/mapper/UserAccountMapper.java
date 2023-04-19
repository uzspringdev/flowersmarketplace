package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Role;
import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserAccountMapper {

    UserAccountMapper getInstance = Mappers.getMapper(UserAccountMapper.class);

    UserAccount toEntity(UserAccountDto userAccountDto);

    UserAccountDto toDto(UserAccount userAccount);

    default UserAccount updateFromDto(UserAccountDto userAccountDto, UserAccount userAccount) {
        if (userAccountDto == null) {
            return null;
        }
        if (userAccountDto.getId() != null)
            userAccount.setId(userAccountDto.getId());
        if (userAccountDto.getUsername() != null)
            userAccount.setUsername(userAccountDto.getUsername());
        if (userAccountDto.getPassword() != null)
            userAccount.setPassword(userAccountDto.getPassword());
        if (userAccountDto.getUserType() != null)
            userAccount.setUserType(userAccountDto.getUserType());
        if (userAccountDto.getRoles() != null) {
            Set<Role> userRoles = userAccount.getRoles();
            Set<Role> newRoles = userAccountDto.getRoles();
            userRoles.addAll(newRoles);
            userAccount.setRoles(userRoles);
        }

        return userAccount;
    }
}
