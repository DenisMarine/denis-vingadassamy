package com.party.Party.service;

import com.party.Party.dto.ParticipantDto;
import com.party.Party.entity.Participant;
import com.party.Party.mapper.ParticipantMapper;
import com.party.Party.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    public List<ParticipantDto> findAll(Pageable pageable) {
        return participantMapper.toDtos(participantRepository.findAll(pageable).getContent());
    }

    public ParticipantDto findById(Long id) {
        return participantMapper.toDto(participantRepository.findById(id).orElse(null));
    }

    public ParticipantDto save(ParticipantDto participantDto) {
        Participant participant = participantMapper.toEntity(participantDto);
        return participantMapper.toDto(participantRepository.save(participant));
    }

    public ParticipantDto update(Long id, ParticipantDto participantDto) {
        Participant updatedParticipant = participantMapper.toEntity(participantDto);
        Participant existingParticipant = participantRepository.findById(id).orElse(null);
        if (existingParticipant != null) {
            updatedParticipant.setId(existingParticipant.getId());
            return participantMapper.toDto(participantRepository.save(updatedParticipant));
        }
        return null;
    }

    public void delete(Long id) {
        participantRepository.deleteById(id);
    }
}
