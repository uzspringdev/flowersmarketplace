package com.example.flowers_marketplace.dto;

import com.example.flowers_marketplace.model.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BalanceDto {

    private Long id;

    private Double amount;

    private Currency currency;
}
