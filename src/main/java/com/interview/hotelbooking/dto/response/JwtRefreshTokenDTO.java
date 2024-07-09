package com.interview.hotelbooking.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtRefreshTokenDTO {
    private String refreshToken;
}
