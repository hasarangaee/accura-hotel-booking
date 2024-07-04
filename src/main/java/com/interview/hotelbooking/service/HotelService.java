package com.interview.hotelbooking.service;

import com.interview.hotelbooking.dto.HotelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {

    ResponseEntity<?> create(HotelDTO hotelDTO);

    ResponseEntity<?> search(String location, double review);
}
