package com.party.Party.repository;

import com.party.Party.entity.UserRatingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRatingViewRepository extends JpaRepository<UserRatingView, Long> {
    @Query("SELECT u FROM UserRatingView u WHERE u.profileId = :profileId")
    Optional<UserRatingView> findByProfileId(Long profileId);
}
