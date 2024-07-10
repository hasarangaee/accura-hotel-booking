package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.HotelDTO;
import com.interview.hotelbooking.dto.RoomDTO;
import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.repository.HotelRepository;
import com.interview.hotelbooking.repository.RoomRepository;
import com.interview.hotelbooking.service.CurrentUser;
import com.interview.hotelbooking.service.HotelService;
import com.interview.hotelbooking.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Override
    public ResponseEntity<?> create(HotelDTO hotelDTO) {
        DataResponse dataResponse = new DataResponse();
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setUser(currentUser.getUser());
        hotelRepository.save(hotel);
        dataResponse.setDescription("Hotel created");
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> search(String location, double review) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(hotelRepository.findByLocationOrReviewGreaterThanEqual(location, review));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getMyHotels() {
        DataResponse dataResponse = new DataResponse();
        List<Hotel> hotels = hotelRepository.findAllByUser(currentUser.getUser());
        dataResponse.setData(modelMapper.map(hotels, new TypeToken<List<HotelDTO>>() {
        }.getType()));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        DataResponse dataResponse = new DataResponse();
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) return ResponseUtils.badRequestResponse("Hotel not found");

        dataResponse.setData(modelMapper.map(hotel, HotelDTO.class));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        DataResponse dataResponse = new DataResponse();
        List<Hotel> hotels = hotelRepository.findAll();

//            hotelDTO.setTotalRooms(hotel.getRooms().size()); // Not Good in this situation

        List<HotelDTO> hotelDTO = hotels.stream()
                .map(hotel -> {
                    HotelDTO dto = modelMapper.map(hotel, HotelDTO.class);
                    dto.setTotalRooms(roomRepository.countAllByHotel(hotel));
                    dto.setAvailableRooms(roomRepository.countAllByHotelAndIsBookingFalse(hotel));
                    return dto;
                }).toList();
        dataResponse.setData(hotelDTO);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
