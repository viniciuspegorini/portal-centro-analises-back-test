package com.portal.centro.API.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RecoverPasswordDTO {

    @NotNull
    private String username;
    @NotNull
    private Integer code;
    @NotNull
    private String newPassword;

}
