package com.interview.hotelbooking.service;

import com.interview.hotelbooking.dto.user.UserAuth;
import com.interview.hotelbooking.dto.user.UserCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    ResponseEntity<?> registerUser(UserCreate register);

    ResponseEntity<?> authenticateUser(UserAuth login);
}
