package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.dto.CardDto;
import com.example.flowers_marketplace.mapper.CardMapper;
import com.example.flowers_marketplace.repository.CardRepository;
import com.example.flowers_marketplace.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final CardMapper cardMapper = CardMapper.getInstance;
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card save(CardDto cardDto) {
        Card card = cardMapper.toEntity(cardDto);
        return cardRepository.save(card);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElse(null);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card update(Long id, CardDto cardDto) {
        if (cardRepository.existsById(id)) {
            Card card = cardMapper.toEntity(cardDto);
            return cardRepository.save(card);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Card> saveAll(List<CardDto> cardDtoList) {
        List<Card> cards = cardMapper.toEntityList(cardDtoList);

        return cardRepository.saveAll(cards);
    }


}
