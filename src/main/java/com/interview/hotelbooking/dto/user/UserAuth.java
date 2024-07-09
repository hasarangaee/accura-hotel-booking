package com.interview.hotelbooking.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserAuth {

    @NotNull
    @NotBlank
    private String username, password;
    private Set<String> role;








}
