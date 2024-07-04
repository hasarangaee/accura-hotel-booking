package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.dto.HotelDTO;
import com.interview.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody HotelDTO hotelDTO) {
        return hotelService.create(hotelDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String location, @RequestParam double review) {
        return hotelService.search(location, review);
    }
}
