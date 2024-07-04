package com.interview.hotelbooking.utils;

import com.interview.hotelbooking.dto.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<DataResponse> badRequestResponse(String description) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        dataResponse.setDescription(description);
        return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
    }
}
