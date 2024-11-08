package com.party.Party.mapper;

import com.party.Party.dto.BringItemDto;
import com.party.Party.entity.BringItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParticipantMapper.class, PartyMapper.class})
public interface BringItemMapper {
    @Mapping(source = "party.id", target = "partyId")
    BringItemDto toDto(BringItem bringItem);

    @Mapping(source = "partyId", target = "party.id")
    BringItem toEntity(BringItemDto bringItemDto);

    List<BringItemDto> toDtos(List<BringItem> bringItems);
    List<BringItem> toEntities(List<BringItemDto> bringItemDtos);
}
