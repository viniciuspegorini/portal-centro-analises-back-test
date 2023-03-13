package com.portal.centro.API.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
