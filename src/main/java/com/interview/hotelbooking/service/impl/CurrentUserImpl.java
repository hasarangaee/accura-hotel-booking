package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.model.User;
import com.interview.hotelbooking.repository.UserRepository;
import com.interview.hotelbooking.service.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserImpl implements CurrentUser {
    private final UserRepository userRepository;

    public CurrentUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    }
}
