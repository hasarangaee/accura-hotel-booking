package com.interview.hotelbooking.service;

import com.interview.hotelbooking.enums.ERole;
import com.interview.hotelbooking.enums.UserType;
import com.interview.hotelbooking.model.User;
import com.interview.hotelbooking.model.masters.Role;
import com.interview.hotelbooking.repository.RoleRepository;
import com.interview.hotelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultDataService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public void createDefaultRoles(){
        for (ERole role : ERole.values()) {
            if (roleRepository.findByName(role).isEmpty()) {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            }
        }
    }

    public void createDefaultAdminUser(){
        String adminUsername = "admin";
        String adminPassword = "admin";

        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            HashSet<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User user = new User();
            user.setUsername(adminUsername);
            user.setPassword(encoder.encode(adminPassword));
            user.setName(adminUsername);
            user.setUserType(UserType.SYSTEM);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
