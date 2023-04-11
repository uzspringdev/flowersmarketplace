package com.example.flowers_marketplace.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CardDto {
    private String cardName;

    private String ownerName;

    private String cardNumber;

    private String expiryDate;

    private BalanceDto balanceDto;
}
