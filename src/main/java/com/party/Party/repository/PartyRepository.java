package com.party.Party.repository;

import com.party.Party.entity.Party;
import com.party.Party.utils.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query("SELECT p FROM Party p " +
            "LEFT JOIN FETCH p.createdBy cb " +
            "LEFT JOIN FETCH p.address a " +
            "WHERE p.id = :partyId")
    Optional<Party> findPartyById(@Param("partyId") Long partyId);

    @Query("SELECT p FROM Party p " +
            "LEFT JOIN FETCH p.createdBy cb " +
            "LEFT JOIN FETCH p.address a " +
            "WHERE cb.id = :createdById")
    Page<Party> findByCreatedById(@Param("createdById") Long createdById, Pageable pageable);

    @Query("SELECT p FROM Party p " +
            "LEFT JOIN FETCH p.createdBy cb " +
            "LEFT JOIN FETCH p.address a " +
            "WHERE p.partyType = :partyType")
    Page<Party> findByPartyType(@Param("partyType") PartyType partyType, Pageable pageable);

    @Query("SELECT p FROM Party p " +
            "LEFT JOIN FETCH p.createdBy cb " +
            "LEFT JOIN FETCH p.address a " +
            "WHERE p.name = :partyName")
    Optional<Party> findByName(@Param("partyName") String partyName);

    @Query("SELECT p FROM Party p " +
            "LEFT JOIN FETCH p.createdBy cb " +
            "LEFT JOIN FETCH p.address a " +
            "WHERE p.deleteDate IS NULL")
    Set<Party> findActiveParties();
}
