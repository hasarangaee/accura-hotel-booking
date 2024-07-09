package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.dto.RoomDTO;
import com.interview.hotelbooking.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<?> create(@Valid @RequestBody RoomDTO dto) {
        return roomService.create(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return roomService.getAll();
    }

    @GetMapping("/search/{number}")
    public ResponseEntity<?> search(@PathVariable int number) {
        return roomService.search(number);
    }

    @PutMapping
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<?> update(@RequestBody RoomDTO dto) {
        return roomService.update(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return roomService.delete(id);
    }
}
