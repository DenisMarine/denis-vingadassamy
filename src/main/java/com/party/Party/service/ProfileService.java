package com.party.Party.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party.Party.dto.*;
import com.party.Party.entity.Profile;
import com.party.Party.entity.UserRatingView;
import com.party.Party.mapper.*;
import com.party.Party.repository.ProfileRepository;
import com.party.Party.repository.UserRatingViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;
    private ProfileMapper profileMapper;
    private AddressMapper addressMapper;
    private UserMapper userMapper;
    private ObjectMapper objectMapper;
    private UserRatingViewMapper userRatingViewMapper;
    private final UserRatingViewRepository userRatingViewRepository;

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
        ProfileDto profileDto = profileMapper.toDto(profileRepository.saveProfile(profile));
        refreshMaterializedView();
        return profileDto;
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

        ProfileDto profileDto = profileMapper.toDto(profileRepository.updateProfile(profileUpdateDto.getAge(),
                objectMapper.valueToTree(profileUpdateDto.getInterests()).toString(), profileUpdateDto.getUsername(), profileId));
        refreshMaterializedView();
        return profileDto;
    }

    public void deleteProfile(Long profileId) {
        getProfile(profileId);

        profileRepository.deleteProfileById(OffsetDateTime.now(), profileId);
        refreshMaterializedView();
    }

    public UserRatingViewDto getUserRating(Long profileId) {
        Profile profile = getProfile(profileId);
        return userRatingViewMapper.toDto(userRatingViewRepository.findByProfileId(profileId)
                .orElse(new UserRatingView(profileId, profile.getUsername(), 0)));
    }

    private Profile getProfile(Long profileId) {
        return profileRepository.findProfileById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile with id " + profileId + " not found"));
    }

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW user_average_rating");
    }
}
