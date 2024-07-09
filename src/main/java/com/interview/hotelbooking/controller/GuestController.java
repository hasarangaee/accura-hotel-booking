package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview/api/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return guestService.getAll();
    }
}
