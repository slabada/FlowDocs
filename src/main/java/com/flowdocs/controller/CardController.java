package com.flowdocs.controller;

import com.flowDocs.api.CardApi;
import com.flowDocs.model.CardDto;
import com.flowdocs.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController implements CardApi {

    private final CardService cardService;

    @Override
    public ResponseEntity<CardDto> getCard(Long id) {
        CardDto card = cardService.getCardById(id);
        return ResponseEntity.ok().body(card);
    }
}
