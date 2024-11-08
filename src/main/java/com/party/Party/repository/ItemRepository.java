package com.party.Party.repository;

import com.party.Party.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i " +
            "JOIN FETCH i.party " +
            "WHERE i.party.id = :partyId")
    List<Item> findAllByPartyId(Long partyId);

    @Query("SELECT i FROM Item i " +
            "JOIN FETCH i.party " +
            "WHERE i.party.id = :partyId AND i.type = :type")
    List<Item> findByPartyIdAndType(Long partyId, String type);

    @Query("SELECT i FROM Item i " +
            "JOIN FETCH i.party " +
            "WHERE i.type = :type")
    List<Item> findByType(String type);

    @Query("SELECT i FROM Item i " +
            "JOIN FETCH i.party " +
            "WHERE i.id = :itemId")
    Optional<Item> findById(Long itemId);
}
