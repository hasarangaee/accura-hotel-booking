package com.interview.hotelbooking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GuestService {

    ResponseEntity<?> getAll();
}
