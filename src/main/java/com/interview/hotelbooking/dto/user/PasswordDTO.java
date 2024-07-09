package com.interview.hotelbooking.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PasswordDTO {
    private String oldPassword;
    private String newPassword;

    private Long id;
    private String email;
    private String code;
}
