package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.HotelDTO;
import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.model.Hotel;
import com.interview.hotelbooking.repository.HotelRepository;
import com.interview.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> create(HotelDTO hotelDTO) {
        DataResponse dataResponse = new DataResponse();
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
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
}
