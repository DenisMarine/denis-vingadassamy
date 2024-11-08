package com.party.Party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingViewDto {

    private Long profileId;
    private String username;
    private float averageRating;
}
