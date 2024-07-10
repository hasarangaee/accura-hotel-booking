package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.enums.UserType;
import com.interview.hotelbooking.model.Guest;
import com.interview.hotelbooking.repository.GuestRepository;
import com.interview.hotelbooking.repository.UserRepository;
import com.interview.hotelbooking.service.GuestService;
import com.interview.hotelbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> getAll() {
        DataResponse response = new DataResponse();
        response.setData(modelMapper.map(userRepository.findAllByUserType(UserType.USER), new TypeToken<List<Guest>>() {
        }.getType()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
