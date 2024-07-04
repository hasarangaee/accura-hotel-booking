package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.RoomDTO;
import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.model.Guest;
import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.model.Room;
import com.interview.hotelbooking.repository.GuestRepository;
import com.interview.hotelbooking.repository.HotelRepository;
import com.interview.hotelbooking.repository.RoomRepository;
import com.interview.hotelbooking.service.GuestService;
import com.interview.hotelbooking.service.RoomService;
import com.interview.hotelbooking.utils.ResponseUtils;
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
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> getAll() {
        DataResponse response = new DataResponse();
        response.setData(modelMapper.map(guestRepository.findAll(), new TypeToken<List<Guest>>() {
        }.getType()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
