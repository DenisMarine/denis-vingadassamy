package com.party.Party.dto;

import com.party.Party.utils.PartyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDto {
    private Long id;
    private String name;
    private PartyType partyType;
    private int nbPlaces;
    private boolean paid;
    private float price;
    private OffsetDateTime creationDate;
    private OffsetDateTime deleteDate;
    private OffsetDateTime updateDate;
    private AddressDto address;
    private ProfileDto createdBy;
    private Set<ParticipantDto> participants;
    private Set<ItemDto> items;
    private Set<BringItemDto> bringItems;
}
