package com.example.flowers_marketplace.service;


import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.dto.CardDto;

import java.util.List;

public interface CardService {
    Card save(CardDto cardDto);

    Card save(Card card);

    Card findById(Long id);

    List<Card> findAll();

    Card update(Long id, CardDto cardDto);

    Boolean delete(Long id);

    List<Card> saveAll(List<CardDto> cardDtoList);

}
