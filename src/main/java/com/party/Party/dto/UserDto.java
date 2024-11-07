package com.party.Party.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
