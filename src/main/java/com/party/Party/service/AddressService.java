package com.party.Party.service;

import com.party.Party.dto.AddressDto;
import com.party.Party.entity.Address;
import com.party.Party.mapper.AddressMapper;
import com.party.Party.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    public AddressDto getById(Long id) {
        return addressMapper.toDto(getAddressById(id));
    }

    public List<AddressDto> getAll(int page, int pageSize) {
        return addressRepository.findAllAddresses(PageRequest.of(page, pageSize)).getContent()
                .stream().map(address -> addressMapper.toDto(address)).toList();
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        return addressMapper.toDto(addressRepository.save(address));
    }

    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        Address address = getAddressById(id);
        address.setCompleteAddress(addressDto.getCompleteAddress());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setRegion(addressDto.getRegion());
        return addressMapper.toDto(addressRepository.save(address));
    }

    public void deleteAddressById(Long addressId) {
        getAddressById(addressId);
        addressRepository.deleteById(addressId);
    }

    private Address getAddressById (Long addressId) {
        return addressRepository.findAddressById(addressId)
                .orElseThrow(() -> new NotFoundException("Address with id " + addressId + " not found"));
    }
}
