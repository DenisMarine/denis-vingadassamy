package com.party.Party.repository;

import com.party.Party.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.address " +
            "JOIN FETCH p.comments " +
            "JOIN FETCH p.commentsWrite " +
            "WHERE p.id = :profileId")
    Optional<Profile> findProfileById(Long profileId);

    @Query("SELECT p FROM Profile p " +
            "LEFT JOIN FETCH p.address")
    Page<Profile> findAllProfilesWithAddress(Pageable pageable);

    @Query(value = "INSERT INTO profile (address_id,age,creation_date,delete_date,interests,update_date,user_id,username) " +
            "values (:#{#profile.address.id}, :#{#profile.age}, :#{#profile.creationDate}, :#{#profile.deleteDate}, " +
            "(:#{#profile.interests})::jsonb, :#{#profile.updateDate}, :#{#profile.user.id}, :#{#profile.username}) " +
            "RETURNING *",
    nativeQuery = true)
    Profile saveProfile(Profile profile);
}
