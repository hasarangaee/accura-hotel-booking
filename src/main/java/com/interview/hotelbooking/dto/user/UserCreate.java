package com.interview.hotelbooking.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class UserCreate {

    @NotNull
    @NotBlank
    private String username, password;

    private String name;

    @NotNull
    private Set<String> role;

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
}
