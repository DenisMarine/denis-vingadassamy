package com.party.Party.mapper;

import com.party.Party.dto.ParticipantDto;
import com.party.Party.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, PartyMapper.class})
public interface ParticipantMapper {
    @Mapping(source = "party.id", target = "partyId")
    ParticipantDto toDto(Participant participant);
    @Mapping(source = "partyId", target = "party.id")
    Participant toEntity(ParticipantDto participantDto);
    List<ParticipantDto> toDtos(List<Participant> participants);
    List<Participant> toEntities(List<ParticipantDto> participantDtos);
}
