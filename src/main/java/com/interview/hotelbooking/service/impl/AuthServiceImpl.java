package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.dto.response.JwtResponseDto;
import com.interview.hotelbooking.dto.user.UserAuth;
import com.interview.hotelbooking.dto.user.UserCreate;
import com.interview.hotelbooking.enums.ERole;
import com.interview.hotelbooking.enums.UserType;
import com.interview.hotelbooking.model.User;
import com.interview.hotelbooking.model.masters.Role;
import com.interview.hotelbooking.repository.RoleRepository;
import com.interview.hotelbooking.repository.UserRepository;
import com.interview.hotelbooking.security.jwt.JwtUtils;
import com.interview.hotelbooking.security.services.UserDetailsImpl;
import com.interview.hotelbooking.service.AuthService;
import com.interview.hotelbooking.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> registerUser(UserCreate dto) {
        DataResponse response = new DataResponse();

        if (userRepository.existsByUsername(dto.getUsername()))
            return ResponseUtils.badRequestResponse("Error: Username is already registered!");

        Set<String> strRoles = dto.getRole();
        Set<Role> roles = strRoles.stream()
                .map(role -> switch (role) {
                    case "admin" -> roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    case "hotel" -> roleRepository.findByName(ERole.ROLE_HOTEL).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    default -> throw new RuntimeException("Error: Role is not recognized.");
                })
                .collect(Collectors.toSet());

        User user = modelMapper.map(dto, User.class);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setUserType(UserType.USER);
        user.setRoles(roles);
        userRepository.save(user);

        response.setDescription("Registration Successful.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> authenticateUser(UserAuth login) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUsername(userDetails);

            return ResponseEntity.ok(new JwtResponseDto(userDetails.getId(), token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
