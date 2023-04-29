package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Admin;
import com.example.flowers_marketplace.domain.Role;
import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.AdminDto;
import com.example.flowers_marketplace.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface AdminMapper {

    AdminMapper getInstance = Mappers.getMapper(AdminMapper.class);

    Admin toEntity(AdminDto adminDto);

    AdminDto toDto(Admin admin);

    UserAccount toUserAccountEntity(UserAccountDto userAccountDto);

    UserAccountDto toUserAccountDto(UserAccount userAccount);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

    default Admin updateFromDto(AdminDto adminDto, Admin admin) {
        if (adminDto == null) {
            return admin;
        }
        if (adminDto.getId() != null)
            admin.setId(adminDto.getId());
        if (adminDto.getUserAccount() != null)
            admin.setUserAccount(updateUserAccountFromDto(adminDto.getUserAccount(), admin.getUserAccount()));
        if (adminDto.getFirstName() != null)
            admin.setFirstName(adminDto.getFirstName());
        if (adminDto.getLastName() != null)
            admin.setLastName(adminDto.getLastName());
        if (adminDto.getPhoneNumber() != null)
            admin.setPhoneNumber(adminDto.getPhoneNumber());
        if (adminDto.getEmail() != null)
            admin.setEmail(adminDto.getEmail());
        if (adminDto.getAddress() != null)
            admin.setAddress(updateAddressFromDto(adminDto.getAddress(), admin.getAddress()));
        if (adminDto.getStatus() != null)
            admin.setStatus(adminDto.getStatus());
        if (adminDto.getLangKey() != null)
            admin.setLangKey(adminDto.getLangKey());

        return admin;
    }

    private UserAccount updateUserAccountFromDto(UserAccountDto userAccountDto, @MappingTarget UserAccount userAccount) {
        if (userAccountDto == null) {
            return userAccount;
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
            Set<Role> roles = userAccount.getRoles();
            roles.addAll(userAccountDto.getRoles());
            userAccount.setRoles(roles);
        }

        return userAccount;
    }

    private Address updateAddressFromDto(AddressDto addressDto, @MappingTarget Address address) {
        if (addressDto == null) {
            return address;
        }
        if (addressDto.getId() != null)
            address.setId(addressDto.getId());
        if (addressDto.getDistrict() != null)
            address.setDistrict(addressDto.getDistrict());
        if (addressDto.getStreet() != null)
            address.setStreet(addressDto.getStreet());
        if (addressDto.getApartment() != null)
            address.setApartment(addressDto.getApartment());
        if (addressDto.getFloor() != null)
            address.setFloor(addressDto.getFloor());
        if (addressDto.getFlatNumber() != null)
            address.setFlatNumber(addressDto.getFlatNumber());
        if (addressDto.getLatitude() != null)
            address.setLatitude(addressDto.getLatitude());
        if (addressDto.getLongitude() != null)
            address.setLongitude(addressDto.getLongitude());

        return address;
    }

}
