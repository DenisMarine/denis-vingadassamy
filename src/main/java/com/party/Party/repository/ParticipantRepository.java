package com.party.Party.repository;

import com.party.Party.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("SELECT p FROM Participant p " +
            "JOIN FETCH p.party " +
            "JOIN FETCH p.profile " +
            "WHERE p.id = :participantId")
    Optional<Participant> findById(Long participantId);

    @Query("SELECT p FROM Participant p " +
            "JOIN FETCH p.party " +
            "JOIN FETCH p.profile " +
            "WHERE p.party.id = :partyId")
    List<Participant> findAllByPartyId(Long partyId);

    @Query("SELECT p FROM Participant p " +
            "JOIN FETCH p.party " +
            "JOIN FETCH p.profile " +
            "WHERE p.profile.id = :profileId")
    List<Participant> findAllByProfileId(Long profileId);
}
