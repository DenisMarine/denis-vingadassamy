package com.party.Party.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String username;
    private int age;
    private List<String> interests;
    @NonNull
    @Valid
    private AddressDto addressDto;
    @NonNull
    @Valid
    private UserDto userDto;
    private List<CommentDto> commentDtos;
    private List<CommentDto> commentsWriteDto;
}
