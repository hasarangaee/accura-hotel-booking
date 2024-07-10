package com.interview.hotelbooking.repository;

import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByUser(User user);

    List<Hotel> findByLocationOrReviewGreaterThanEqual(String location, double review);
}
