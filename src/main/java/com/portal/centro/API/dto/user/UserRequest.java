package com.portal.centro.API.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Size(min = 6, max = 20)
    private String username;

    @Getter
    @Setter
    @NotNull(message = "password must contain at least 8 characters!")
    @Size(min = 8, max = 100)
    private String password;

    @NotNull(message = "email must be valid!")
    @Email
    @Getter
    private String email;
}
