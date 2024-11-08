package com.party.Party.repository;

import com.party.Party.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p " +
            "LEFT JOIN FETCH p.profile pr " +
            "LEFT JOIN FETCH p.party pa " +
            "WHERE p.id = :participantId")
    Optional<Participant> findParticipantById(@Param("participantId") Long participantId);

    @Query("SELECT p FROM Participant p " +
            "LEFT JOIN FETCH p.profile pr " +
            "LEFT JOIN FETCH p.party pa " +
            "WHERE pa.id = :partyId")
    Page<Participant> findByPartyId(@Param("partyId") Long partyId, Pageable pageable);

    @Query("SELECT p FROM Participant p " +
            "LEFT JOIN FETCH p.profile pr " +
            "LEFT JOIN FETCH p.party pa " +
            "WHERE pr.id = :profileId")
    Page<Participant> findByProfileId(@Param("profileId") Long profileId, Pageable pageable);

    @Query("SELECT p FROM Participant p " +
            "LEFT JOIN FETCH p.profile pr " +
            "LEFT JOIN FETCH p.party pa " +
            "WHERE p.accepted = :accepted")
    Page<Participant> findByAccepted(@Param("accepted") boolean accepted, Pageable pageable);

    @Query("SELECT p FROM Participant p " +
            "LEFT JOIN FETCH p.profile pr " +
            "LEFT JOIN FETCH p.party pa " +
            "WHERE p.hasPaid = :hasPaid")
    Page<Participant> findByHasPaid(@Param("hasPaid") boolean hasPaid, Pageable pageable);
}
