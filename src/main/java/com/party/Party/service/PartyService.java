package com.party.Party.service;

import com.party.Party.dto.AddressDto;
import com.party.Party.dto.BringItemDto;
import com.party.Party.dto.PartyCreateDto;
import com.party.Party.dto.PartyDto;
import com.party.Party.entity.Address;
import com.party.Party.entity.BringItem;
import com.party.Party.entity.Party;
import com.party.Party.mapper.AddressMapper;
import com.party.Party.mapper.BringItemMapper;
import com.party.Party.mapper.PartyMapper;
import com.party.Party.mapper.ProfileMapper;
import com.party.Party.repository.AddressRepository;
import com.party.Party.repository.BringItemRepository;
import com.party.Party.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final PartyMapper partyMapper;
    private final AddressMapper addressMapper;
    private final BringItemMapper bringItemMapper;
    private final ProfileMapper profileMapper;
    private final AddressRepository addressRepository;
    private final BringItemRepository bringItemRepository;
    private final ProfileService profileService;

    public List<PartyDto> findAll(Pageable pageable) {
        return partyMapper.toDtos(partyRepository.findAll(pageable).getContent());
    }

    public PartyDto findById(Long id) {
        return partyMapper.toDto(partyRepository.findById(id).orElse(null));
    }

    public PartyDto save(PartyCreateDto partyCreateDto) {
        AddressDto addressDto = partyCreateDto.getAddress();
        Address address;
        if (Objects.isNull(addressDto.getId())) {
            address = addressRepository.save(addressMapper.toEntity(addressDto));
        }
        else {
            address = getAddress(addressDto.getId());
        }

        Party party = new Party();
        party.setAddress(address);
        party.setPartyType(partyCreateDto.getPartyType());
        party.setCreatedBy(profileMapper.toEntity(profileService.getById(partyCreateDto.getCreatedBy())));
        party.setName(partyCreateDto.getName());
        party.setNbPlaces(partyCreateDto.getNbPlaces());
        party.setPaid(partyCreateDto.isPaid());
        party.setPrice(partyCreateDto.getPrice());
        party.setCreationDate(OffsetDateTime.now());
        party.setUpdateDate(OffsetDateTime.now());

        /* Does not work

        Set<BringItem> bringItems = partyCreateDto.getBringItemDto()
                .stream().map(bringItemMapper::toEntity).collect(Collectors.toSet());

        bringItems.forEach(bringItem -> bringItem.setParty(party));
        party.setBringItems(bringItems);

         */

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

    private Address getAddress(Long addressId) {
        return addressRepository.findAddressById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }
}
