package com.interview.hotelbooking.exceptions;

import com.interview.hotelbooking.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        return getExceptionErrorResponse(ex.getBindingResult());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeError(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTime(new Date().toString());
        response.setErrors(ex.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ErrorResponse> getExceptionErrorResponse(BindingResult errors) {
        List<Map<String, String>> fieldErrorList = errors.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> fieldErrorMap = new HashMap<>();
                    fieldErrorMap.put("field", error.getField());
                    fieldErrorMap.put("defaultMessage", error.getDefaultMessage());
                    return fieldErrorMap;
                })
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse();
        response.setTime(new Date().toString());
        response.setErrors(fieldErrorList);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
