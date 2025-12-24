package com.flowdocs.service;

import com.flowDocs.model.CardDto;
import com.flowdocs.model.CardModel;
import com.flowdocs.mapper.CardMapper;
import com.flowdocs.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Override
    public CardDto getCardById(Long id) {
        CardModel card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("карточка не найдена"));
        return cardMapper.toDto(card);
    }
}
