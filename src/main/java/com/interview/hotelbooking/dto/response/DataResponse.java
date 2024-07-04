package com.interview.hotelbooking.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class DataResponse {
    private String description,time,referenceNo,code;
    private Integer totalPages;
    private Long totalRecords;
    private Object data;


    public DataResponse() {
        this.time = new Date().toString();
    }
}
