package com.party.Party.mapper;

import com.party.Party.dto.BringItemDto;
import com.party.Party.entity.BringItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BringItemMapper {
    BringItemDto toDto(BringItem bringItem);
    BringItem toEntity(BringItemDto bringItemDto);
    List<BringItemDto> toDtos(List<BringItem> bringItems);
    List<BringItem> toEntities(List<BringItemDto> bringItemDtos);
}
