package com.interview.hotelbooking.repository;

import com.interview.hotelbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomHotelLocationAndCheckInBetweenAndCheckOutBetween(
            String location, LocalDateTime checkInStart, LocalDateTime checkInEnd,
            LocalDateTime checkOutStart, LocalDateTime checkOutEnd
    );
}
