package com.party.Party.service;

import com.party.Party.dto.PartyDto;
import com.party.Party.entity.Party;
import com.party.Party.mapper.PartyMapper;
import com.party.Party.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final PartyMapper partyMapper;

    public List<PartyDto> findAll(Pageable pageable) {
        return partyMapper.toDtos(partyRepository.findAll(pageable).getContent());
    }

    public PartyDto findById(Long id) {
        return partyMapper.toDto(partyRepository.findById(id).orElse(null));
    }

    public PartyDto save(PartyDto partyDto) {
        Party party = partyMapper.toEntity(partyDto);
        return partyMapper.toDto(partyRepository.save(party));
    }

    public PartyDto update(Long id, PartyDto partyDto) {
        Party updatedParty = partyMapper.toEntity(partyDto);
        Party existingParty = partyRepository.findById(id).orElse(null);
        if (existingParty != null) {
            updatedParty.setId(existingParty.getId());
            return partyMapper.toDto(partyRepository.save(updatedParty));
        }
        return null;
    }

    public void delete(Long id) {
        partyRepository.deleteById(id);
    }
}
