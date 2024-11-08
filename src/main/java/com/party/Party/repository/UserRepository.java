package com.party.Party.repository;

import com.party.Party.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "WHERE u.id = :id"
    )
    Optional<User> findUserById(@Param("id") Long id);

    @Query("SELECT DISTINCT u FROM User u ")
    Page<User> findAllUsers(Pageable pageable);

    @Query("SELECT u FROM User u " +
            "WHERE u.email = :email"
    )
    Optional<User> findUserByEmail(@Param("email") String email);
}
