package com.party.Party.service;

import com.party.Party.dto.AddressDto;
import com.party.Party.entity.Address;
import com.party.Party.mapper.AddressMapper;
import com.party.Party.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    public AddressDto createAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        return addressMapper.toDto(addressRepository.save(address));
    }
}
