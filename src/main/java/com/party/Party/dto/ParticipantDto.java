package com.party.Party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {
    private Long id;
    private ProfileDto profile;
    private Long partyId;
    private boolean accepted;
    private boolean hasPaid;
    private OffsetDateTime participationDate;
}
