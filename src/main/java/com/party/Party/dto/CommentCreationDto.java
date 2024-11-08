package com.party.Party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreationDto {

    private Long id;
    private String text;
    private int rating;
    private Long authorId;
    private Long commentedProfileId;
}