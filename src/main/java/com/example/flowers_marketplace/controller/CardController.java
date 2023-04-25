package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.dto.CardDto;
import com.example.flowers_marketplace.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CardDto cardDto) {
        return ResponseEntity.ok(cardService.save(cardDto));
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Card card = cardService.findById(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll() {
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody CardDto cardDto) {
        Card card = cardService.update(id, cardDto);

        return ResponseEntity.ok(card);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        return cardService.delete(id) ? ResponseEntity.ok("Card is deleted") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }
}
