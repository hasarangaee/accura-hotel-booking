package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.dto.BookingDTO;
import com.interview.hotelbooking.dto.HotelDTO;
import com.interview.hotelbooking.model.Booking;
import com.interview.hotelbooking.service.BookingService;
import com.interview.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/interview/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BookingDTO dto) {
        return bookingService.create(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String location, @RequestParam LocalDateTime checkIn, @RequestParam LocalDateTime checkOut) {
        return bookingService.search(location, checkIn, checkOut);
    }
}
