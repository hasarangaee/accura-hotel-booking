package com.interview.hotelbooking.controller;

import com.interview.hotelbooking.dto.user.UserAuth;
import com.interview.hotelbooking.dto.user.UserCreate;
import com.interview.hotelbooking.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/interview/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreate register){
        return authService.registerUser(register);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserAuth login) {
        return authService.authenticateUser(login);
    }
}
