package com.party.Party.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCreateDto {

    private String username;
    private int age;
    private List<String> interests;
    @NonNull
    @Valid
    private AddressDto addressDto;
}
