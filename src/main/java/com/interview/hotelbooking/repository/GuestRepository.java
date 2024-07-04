package com.interview.hotelbooking.repository;

import com.interview.hotelbooking.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findByPhoneNumber(String phone);
}
