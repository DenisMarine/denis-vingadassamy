package com.party.Party.mapper;

import com.party.Party.dto.ItemDto;
import com.party.Party.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ParticipantMapper.class, PartyMapper.class})
public interface ItemMapper {
    @Mapping(source = "broughtBy", target = "broughtBy")
    @Mapping(source = "party.id", target = "partyId")
    ItemDto toDto(Item item);

    @Mapping(source = "broughtBy", target = "broughtBy")
    @Mapping(source = "partyId", target = "party.id")
    Item toEntity(ItemDto itemDto);
    List<ItemDto> toDtos(List<Item> items);
    List<Item> toEntities(List<ItemDto> itemDtos);
}
