package com.interview.hotelbooking.service;

import com.interview.hotelbooking.dto.BookingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface BookingService {

    ResponseEntity<?> create(BookingDTO dto);

    ResponseEntity<?> search(String location, LocalDateTime checkIn, LocalDateTime checkOut);
}
