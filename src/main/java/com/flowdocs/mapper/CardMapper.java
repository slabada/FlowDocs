package com.flowdocs.mapper;

import com.flowDocs.model.CardDto;
import com.flowdocs.model.CardModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toDto(CardModel entity);


}
