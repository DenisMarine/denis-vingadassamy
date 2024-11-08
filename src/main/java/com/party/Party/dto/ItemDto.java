package com.party.Party.dto;

import com.party.Party.utils.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private ItemType type;
    private int quantity;
    private PartyDto party;
    private ParticipantDto broughtBy;
}
