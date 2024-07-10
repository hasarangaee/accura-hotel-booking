package com.interview.hotelbooking.service;

import com.interview.hotelbooking.dto.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    ResponseEntity<?> create(RoomDTO dto);

    ResponseEntity<?> getAll(long id);

    ResponseEntity<?> search(int number);

    ResponseEntity<?> update(RoomDTO dto);

    ResponseEntity<?> delete(long id);

    ResponseEntity<?> getById(long id);
}
