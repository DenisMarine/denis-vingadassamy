package com.party.Party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private UserDto userDto;
    private ProfileCreateDto profileCreateDto;
}
