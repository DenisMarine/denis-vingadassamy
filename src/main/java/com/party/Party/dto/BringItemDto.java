package com.party.Party.dto;

import com.party.Party.utils.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BringItemDto {
    private Long id;
    private ItemType type;
}
