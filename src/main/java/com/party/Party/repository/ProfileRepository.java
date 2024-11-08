package com.party.Party.repository;

import com.party.Party.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.address " +
            "LEFT JOIN FETCH p.comments " +
            "LEFT JOIN FETCH p.commentsWrite " +
            "WHERE p.id = :profileId AND p.deleteDate IS NULL")
    Optional<Profile> findProfileById(Long profileId);

    @Query("SELECT p FROM Profile p " +
            "LEFT JOIN FETCH p.address " +
            "WHERE p.deleteDate IS NULL")
    Page<Profile> findAllProfilesWithAddress(Pageable pageable);

    @Query(value = "INSERT INTO profile (address_id,age,interests,user_id,username, creation_date, update_date) " +
            "values (:#{#profile.address.id}, :#{#profile.age}, (:#{#profile.interests})::jsonb, " +
            ":#{#profile.user.id}, :#{#profile.username}, :#{#profile.creationDate}, :#{#profile.updateDate}) RETURNING *", nativeQuery = true)
    Profile saveProfile(Profile profile);

    @Query(value = "UPDATE profile SET age = :age, interests = CAST(:interests AS jsonb), username = :username WHERE profile_id = :profileId RETURNING *",
            nativeQuery = true)
    Profile updateProfile(int age, String interests, String username, Long profileId);

    @Query(value = "UPDATE profile SET delete_date = :deleteDate WHERE profile_id = :profileId RETURNING profile_id", nativeQuery = true)
    Long deleteProfileById(OffsetDateTime deleteDate, Long profileId);
}
