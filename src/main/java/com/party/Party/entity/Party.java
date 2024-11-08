package com.party.Party.entity;

import com.party.Party.utils.PartyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;
    private String name;
    private PartyType partyType;
    private int nbPlaces;
    private boolean paid;
    private float price;
    private OffsetDateTime creationDate;
    private OffsetDateTime deleteDate;
    private OffsetDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Profile createdBy;

    @OneToMany(mappedBy = "party")
    private Set<Participant> participants;

    @OneToMany(mappedBy = "party")
    private Set<Item> items;

    @OneToMany(mappedBy = "party")
    private Set<BringItem> bringItems;
}
