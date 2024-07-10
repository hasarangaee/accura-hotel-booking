package com.interview.hotelbooking.repository;

import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByNumber(int number);

    List<Room> findAllByHotelId(Long number);

    Integer countAllByHotel(Hotel hotel);

    Integer countAllByHotelAndIsBookingFalse(Hotel hotel);
}
