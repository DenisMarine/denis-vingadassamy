package com.party.Party.repository;

import com.party.Party.entity.BringItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BringItemRepository extends JpaRepository<BringItem, Long> {

    @Query("SELECT b FROM BringItem b " +
            "JOIN FETCH b.party " +
            "WHERE b.id = :bringItemId")
    Optional<BringItem> findBringItemById(Long bringItemId);

    @Query("SELECT b FROM BringItem b " +
            "JOIN FETCH b.party " +
            "WHERE b.party.id = :partyId")
    List<BringItem> findAllByPartyId(Long partyId);
}
