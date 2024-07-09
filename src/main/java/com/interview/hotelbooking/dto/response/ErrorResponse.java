package com.interview.hotelbooking.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String description,time;
    private Object Errors;

}
