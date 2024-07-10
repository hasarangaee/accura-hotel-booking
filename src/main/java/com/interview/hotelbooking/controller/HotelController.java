package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.dto.HotelDTO;
import com.interview.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<?> create(@Valid @RequestBody HotelDTO hotelDTO) {
        return hotelService.create(hotelDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String location, @RequestParam double review) {
        return hotelService.search(location, review);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<?> getMyHotels() {
        return hotelService.getMyHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return hotelService.getById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return hotelService.getAll();
    }
}
