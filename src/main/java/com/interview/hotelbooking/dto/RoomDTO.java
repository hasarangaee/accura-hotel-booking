package com.interview.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RoomDTO {

    private Long id;

    @NotNull
    @Positive
    private Long hotelId;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    private int number;

    @NotNull
    private double price;
}
