package com.party.Party.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    @NotBlank
    private String city;
    @NotBlank
    private String region;
    @NotBlank
    private String country;
    private String completeAddress;
}
