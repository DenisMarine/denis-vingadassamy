package com.party.Party.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

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
    private String interests;
    private OffsetDateTime deleteDate;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "commentedProfile", cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "writtenBy", cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    private Set<Comment> commentsWrite;
}
