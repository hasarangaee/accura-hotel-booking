package com.interview.hotelbooking.service.impl;

import com.interview.hotelbooking.dto.BookingDTO;
import com.interview.hotelbooking.dto.BookingResponseDTO;
import com.interview.hotelbooking.dto.response.DataResponse;
import com.interview.hotelbooking.model.Booking;
import com.interview.hotelbooking.model.Guest;
import com.interview.hotelbooking.model.Room;
import com.interview.hotelbooking.repository.BookingRepository;
import com.interview.hotelbooking.repository.GuestRepository;
import com.interview.hotelbooking.repository.RoomRepository;
import com.interview.hotelbooking.service.BookingService;
import com.interview.hotelbooking.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> create(BookingDTO dto) {
        DataResponse dataResponse = new DataResponse();

        Guest guest = guestRepository.findByPhoneNumber(dto.getPhoneNumber()).orElseGet(() -> {
            Guest newGuest = new Guest();
            newGuest.setName(dto.getName());
            newGuest.setPhoneNumber(dto.getPhoneNumber());
            return guestRepository.save(newGuest);
        });

        Room room = roomRepository.findById(dto.getRoomId()).orElse(null);
        if (room == null) return ResponseUtils.badRequestResponse("Room not found");

        if(room.getIsBooking()) return ResponseUtils.badRequestResponse("Room is already booked");

        Booking booking = new Booking();
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());

        bookingRepository.save(booking);
        room.setIsBooking(true);
        roomRepository.save(room);

        dataResponse.setDescription("Booking success");
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> search(String location, LocalDateTime checkIn, LocalDateTime checkOut) {
        DataResponse dataResponse = new DataResponse();

        List<Booking> list = bookingRepository.findByRoomHotelLocationAndCheckInBetweenAndCheckOutBetween(
                location, checkIn.minusDays(1), checkIn.plusDays(1), checkOut.minusDays(1), checkOut.plusDays(1)
        );
        dataResponse.setData(modelMapper.map(list, new TypeToken<List<BookingResponseDTO>>() {
        }.getType()));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
