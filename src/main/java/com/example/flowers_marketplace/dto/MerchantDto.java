package com.example.flowers_marketplace.dto;

import com.example.flowers_marketplace.domain.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class MerchantDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Set<Role> roles;

    private AddressDto address;

    private BankAccountDto bankAccount;

    private String rate;
}
