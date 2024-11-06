package com.party.Party.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.party.Party.utils.JsonNodeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;
    private String username;
    private int age;
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode interests;
    private OffsetDateTime deleteDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "commentedProfile")
    private List<Profile> comments;

    @OneToMany(mappedBy = "writtenBy")
    private List<Profile> commentsWrite;
}