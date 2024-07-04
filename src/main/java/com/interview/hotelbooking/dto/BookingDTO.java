package com.interview.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;

    @NotNull
    private Long roomId;

    private Long guestId;

    @NotNull
    @NotBlank
    private String name, phoneNumber;

    @NotNull
    private LocalDateTime checkIn, checkOut;
}
