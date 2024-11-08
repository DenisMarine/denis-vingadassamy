package com.party.Party.mapper;

import com.party.Party.dto.PartyDto;
import com.party.Party.entity.Party;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        AddressMapper.class,
        ProfileMapper.class,
        ItemMapper.class,
        ParticipantMapper.class,
        BringItemMapper.class })
public interface PartyMapper {
    @Mapping(source = "address", target = "address")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "participants", target = "participants")
    @Mapping(source = "items", target = "items")
    @Mapping(source = "bringItems", target = "bringItems")
    PartyDto toDto(Party party);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "participants", target = "participants")
    @Mapping(source = "items", target = "items")
    @Mapping(source = "bringItems", target = "bringItems")
    Party toEntity(PartyDto partyDto);

    List<PartyDto> toDtos(List<Party> parties);
    List<Party> toEntities(List<PartyDto> partyDtos);
}
