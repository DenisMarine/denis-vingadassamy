package com.party.Party.repository;

import com.party.Party.entity.Item;
import com.party.Party.utils.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i " +
            "LEFT JOIN FETCH i.party p " +
            "LEFT JOIN FETCH i.type t " +
            "WHERE i.id = :itemId")
    Optional<Item> findItemById(@Param("itemId") Long itemId);

    @Query("SELECT i FROM Item i " +
            "LEFT JOIN FETCH i.party p " +
            "WHERE p.id = :partyId")
    Page<Item> findByPartyId(@Param("partyId") Long partyId, Pageable pageable);

    @Query("SELECT i FROM Item i " +
            "LEFT JOIN FETCH i.party p " +
            "WHERE i.type = :type")
    Page<Item> findByType(@Param("type") ItemType type, Pageable pageable);

    @Query("SELECT i FROM Item i " +
            "LEFT JOIN FETCH i.party p " +
            "WHERE i.name = :name")
    Page<Item> findByName(@Param("name") String name, Pageable pageable);
}
