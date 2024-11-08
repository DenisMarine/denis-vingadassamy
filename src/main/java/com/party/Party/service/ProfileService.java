package com.party.Party.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party.Party.dto.*;
import com.party.Party.entity.Profile;
import com.party.Party.mapper.AddressMapper;
import com.party.Party.mapper.CommentMapper;
import com.party.Party.mapper.ProfileMapper;
import com.party.Party.mapper.UserMapper;
import com.party.Party.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
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
        Profile profile = getProfile(profileId);
        return profileMapper.toDto(profile);
    }

    public List<ProfileDto> getAllProfiles(int page, int pageSize) {
        return profileRepository.findAllProfilesWithAddress(PageRequest.of(page, pageSize)).getContent()
                .stream().map(profile -> profileMapper.toDto(profile)).toList();
    }

    public ProfileDto updateProfile(Long profileId, ProfileUpdateDto profileUpdateDto) {
        getProfile(profileId);

        return profileMapper.toDto(profileRepository.updateProfile(profileUpdateDto.getAge(),
                objectMapper.valueToTree(profileUpdateDto.getInterests()).toString(), profileUpdateDto.getUsername(), profileId));
    }

    public void deleteProfile(Long profileId) {
        getProfile(profileId);

        profileRepository.deleteProfileById(OffsetDateTime.now(), profileId);
    }

    private Profile getProfile(Long profileId) {
        return profileRepository.findProfileById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile with id " + profileId + " not found"));
    }
}
