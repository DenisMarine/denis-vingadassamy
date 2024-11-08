package com.party.Party.mapper;

import com.party.Party.dto.UserRatingViewDto;
import com.party.Party.entity.UserRatingView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRatingViewMapper {

    UserRatingView toEntity(UserRatingViewDto userRatingViewDto);
    UserRatingViewDto toDto(UserRatingView userRatingView);
}
