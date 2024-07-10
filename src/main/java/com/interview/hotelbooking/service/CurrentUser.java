package com.interview.hotelbooking.service;

import com.interview.hotelbooking.model.User;
import org.springframework.stereotype.Service;

@Service
public interface CurrentUser {
    User getUser();
}
