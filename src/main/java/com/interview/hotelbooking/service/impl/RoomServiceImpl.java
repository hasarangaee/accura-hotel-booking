package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.RoomDTO;
import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.model.Room;
import com.interview.hotelbooking.repository.HotelRepository;
import com.interview.hotelbooking.repository.RoomRepository;
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
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> create(RoomDTO dto) {
        DataResponse dataResponse = new DataResponse();

        Hotel hotel = hotelRepository.findById(dto.getHotelId()).orElse(null);
        if (hotel == null) return ResponseUtils.badRequestResponse("Hotel not found");

        Room room = modelMapper.map(dto, Room.class);
        room.setHotel(hotel);
        room.setIsBooking(false);

        roomRepository.save(room);
        dataResponse.setDescription("Room created");
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAll() {
        DataResponse response = new DataResponse();
        response.setData(modelMapper.map(roomRepository.findAll(), new TypeToken<List<RoomDTO>>() {
        }.getType()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> search(int number) {
        DataResponse response = new DataResponse();
        Room room = roomRepository.findByNumber(number).orElse(null);
        if (room == null) return ResponseUtils.badRequestResponse("Room not found");

        response.setData(modelMapper.map(room, RoomDTO.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RoomDTO dto) {
        DataResponse response = new DataResponse();

        Room room = roomRepository.findById(dto.getId()).orElse(null);
        if (room == null) return ResponseUtils.badRequestResponse("Room not found");

        room.setType(dto.getType());
        room.setNumber(dto.getNumber());
        roomRepository.save(room);

        response.setDescription("Room updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        DataResponse response = new DataResponse();

        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) return ResponseUtils.badRequestResponse("Room not found");

        if(room.getIsBooking()) return ResponseUtils.badRequestResponse("Room is booking");

        roomRepository.delete(room);

        response.setDescription("Room deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
