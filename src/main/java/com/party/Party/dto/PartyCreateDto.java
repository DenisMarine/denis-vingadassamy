package com.party.Party.dto;

import com.party.Party.utils.PartyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyCreateDto {
    private String name;
    private PartyType partyType;
    private int nbPlaces;
    private boolean paid;
    private float price;
    private AddressDto address;
    private Long createdBy;
    private Set<BringItemDto> bringItemDto;
}
