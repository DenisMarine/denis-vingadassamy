package com.party.Party.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_average_rating")
public class UserRatingView {

    @Id
    @Column(name = "profile_id")
    private Long profileId;
    private String username;
    @Column(name = "average_rating")
    private float averageRating;
}
