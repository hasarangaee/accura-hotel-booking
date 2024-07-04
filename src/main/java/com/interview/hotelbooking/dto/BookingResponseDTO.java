package com.interview.hotelbooking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private String guestName, guestPhoneNumber, roomNumber, roomPrice;
    private LocalDateTime checkIn, checkOut;
}
