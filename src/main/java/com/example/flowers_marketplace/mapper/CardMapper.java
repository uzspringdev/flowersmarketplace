package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.dto.CardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper getInstance = Mappers.getMapper(CardMapper.class);

    Card toEntity(CardDto cardDto);

    CardDto toDto(Card card);

    List<Card> toEntityList(List<CardDto> cardDtoList);

}
