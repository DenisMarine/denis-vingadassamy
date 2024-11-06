package com.party.Party.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String text;
    private int rating;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commented_profile", referencedColumnName = "profile_id", nullable = false)
    private Profile commentedProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "written_by", referencedColumnName = "profile_id", nullable = false)
    private Profile writtenBy;
}
