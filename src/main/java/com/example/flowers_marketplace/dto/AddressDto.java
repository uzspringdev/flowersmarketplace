package com.example.flowers_marketplace.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class AddressDto {
    private String district;

    private String street;

    private String apartment;

    private Byte floor;

    private Integer flatNumber;

    private String latitude;

    private String longitude;

    private LocalDate created_at;

    private LocalDate updated_at;

}
