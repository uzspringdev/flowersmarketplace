package com.example.flowers_marketplace.dto;

import com.example.flowers_marketplace.domain.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private AddressDto addressDto;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
