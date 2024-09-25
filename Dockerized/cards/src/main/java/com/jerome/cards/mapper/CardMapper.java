package com.jerome.cards.mapper;

import com.jerome.cards.dto.CardDto;
import com.jerome.cards.model.CardModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto toDto(CardModel cardModel);
    CardModel toModel(CardDto cardDto);

    void updateCardFromDto(CardDto cardDto, @MappingTarget CardModel cardModel);
}
