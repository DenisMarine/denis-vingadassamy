package com.party.Party.mapper;

import com.party.Party.dto.ItemDto;
import com.party.Party.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item item);
    Item toEntity(ItemDto itemDto);
    List<ItemDto> toDtos(List<Item> items);
    List<Item> toEntities(List<ItemDto> itemDtos);
}
