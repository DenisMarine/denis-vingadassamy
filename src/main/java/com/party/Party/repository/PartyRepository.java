package com.party.Party.repository;

import com.party.Party.entity.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query("SELECT p FROM Party p " +
            "JOIN FETCH p.createdBy " +
            "JOIN FETCH p.address " +
            "WHERE p.id = :partyId")
    Optional<Party> findById(Long partyId);

    @Query("SELECT p FROM Party p " +
            "JOIN FETCH p.createdBy " +
            "JOIN FETCH p.address " +
            "WHERE p.createdBy.id = :createdById")
    Page<Party> findByCreatedById(Long createdById, Pageable pageable);

    @Query("SELECT p FROM Party p " +
            "JOIN FETCH p.createdBy " +
            "JOIN FETCH p.address " +
            "WHERE p.partyType = :partyType")
    Page<Party> findByPartyType(String partyType, Pageable pageable);

    @Query("SELECT p FROM Party p " +
            "JOIN FETCH p.createdBy " +
            "JOIN FETCH p.address " +
            "WHERE p.name = :partyName")
    Optional<Party> findByName(String partyName);

    @Query("SELECT p FROM Party p " +
            "JOIN FETCH p.createdBy " +
            "JOIN FETCH p.address " +
            "WHERE p.deleteDate IS NULL")
    List<Party> findActiveParties();
}
