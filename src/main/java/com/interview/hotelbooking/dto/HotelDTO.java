package com.interview.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private double review;
}
