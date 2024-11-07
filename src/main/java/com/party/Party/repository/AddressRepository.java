package com.party.Party.repository;

import com.party.Party.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a " +
            "WHERE a.id = :addressId"
    )
    Optional<Address> findAddressById(Long addressId);

    @Query("SELECT DISTINCT a FROM Address a ")
    Page<Address> findAllAddresses(Pageable pageable);
}
