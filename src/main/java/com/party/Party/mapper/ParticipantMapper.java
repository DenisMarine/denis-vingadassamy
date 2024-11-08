package com.party.Party.mapper;

import com.party.Party.dto.ParticipantDto;
import com.party.Party.entity.Participant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, PartyMapper.class})
public interface ParticipantMapper {
    ParticipantDto toDto(Participant participant);
    Participant toEntity(ParticipantDto participantDto);
    List<ParticipantDto> toDtos(List<Participant> participants);
    List<Participant> toEntities(List<ParticipantDto> participantDtos);
}
