package com.party.Party.repository;

import com.party.Party.entity.BringItem;
import com.party.Party.utils.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BringItemRepository extends JpaRepository<BringItem, Long> {

    @Query("SELECT b FROM BringItem b " +
            "LEFT JOIN FETCH b.party p " +
            "LEFT JOIN FETCH b.type t " +
            "WHERE b.id = :bringItemId")
    Optional<BringItem> findBringItemById(@Param("bringItemId") Long bringItemId);

    @Query("SELECT b FROM BringItem b " +
            "LEFT JOIN FETCH b.party p " +
            "WHERE p.id = :partyId")
    Page<BringItem> findByPartyId(@Param("partyId") Long partyId, Pageable pageable);

    @Query("SELECT b FROM BringItem b " +
            "LEFT JOIN FETCH b.party p " +
            "WHERE b.type = :type")
    Page<BringItem> findByType(@Param("type") ItemType type, Pageable pageable);
}
