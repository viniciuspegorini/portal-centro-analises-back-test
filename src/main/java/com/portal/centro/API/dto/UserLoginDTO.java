package com.portal.centro.API.dto;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    public UserLoginDTO(User user) {
        this.displayName = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    private String displayName;
    private String email;
    private Type role;

}
