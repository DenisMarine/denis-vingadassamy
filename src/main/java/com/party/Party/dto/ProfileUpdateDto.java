package com.party.Party.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateDto {
    private String username;
    private int age;
    private List<String> interests;
}
