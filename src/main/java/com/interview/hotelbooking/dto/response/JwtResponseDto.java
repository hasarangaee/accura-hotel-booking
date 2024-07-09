package com.interview.hotelbooking.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtResponseDto {
    private Long id;
    private String token;
    private String type = "Bearer";

    public JwtResponseDto(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
