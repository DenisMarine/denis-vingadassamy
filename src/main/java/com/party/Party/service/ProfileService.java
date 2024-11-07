package com.party.Party.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party.Party.dto.ProfileCreateDto;
import com.party.Party.dto.ProfileDto;
import com.party.Party.dto.UserDto;
import com.party.Party.entity.Profile;
import com.party.Party.mapper.AddressMapper;
import com.party.Party.mapper.ProfileMapper;
import com.party.Party.mapper.UserMapper;
import com.party.Party.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AddressService addressService;
    private ProfileMapper profileMapper;
    private AddressMapper addressMapper;
    private UserMapper userMapper;
    private ObjectMapper objectMapper;

    public ProfileDto createProfile(ProfileCreateDto profileCreateDto, UserDto userDto) {
        if (Objects.isNull(profileCreateDto.getAddressDto().getId())) {
            profileCreateDto.setAddressDto(addressService.createAddress(profileCreateDto.getAddressDto()));
        }
        Profile profile = new Profile();
        profile.setAddress(addressMapper.toEntity(profileCreateDto.getAddressDto()));
        profile.setInterests(objectMapper.valueToTree(profileCreateDto.getInterests()).toString());
        profile.setAge(profileCreateDto.getAge());
        profile.setUsername(profileCreateDto.getUsername());
        profile.setCreationDate(OffsetDateTime.now());
        profile.setUser(userMapper.toEntity(userDto));
        OffsetDateTime now = OffsetDateTime.now();
        profile.setCreationDate(now);
        profile.setUpdateDate(now);
        return profileMapper.toDto(profileRepository.saveProfile(profile));
    }

    public ProfileDto getById(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElse(null);
        if (Objects.isNull(profile)) {
            throw new NotFoundException("Profile with id " + profileId + " not found");
        }
        return profileMapper.toDto(profile);
    }
}
