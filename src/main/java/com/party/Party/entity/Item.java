package com.party.Party.entity;

import com.party.Party.utils.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brought_by")
    private Participant broughtBy;
}
