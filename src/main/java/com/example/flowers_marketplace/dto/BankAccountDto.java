package com.example.flowers_marketplace.dto;

import com.example.flowers_marketplace.domain.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class BankAccountDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String accountNumber;

    private List<Card> cards;
}
