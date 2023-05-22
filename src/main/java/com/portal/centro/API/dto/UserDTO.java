package com.portal.centro.API.dto;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    public UserDTO(User user) {
        this.displayName = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    private String displayName;
    private String email;
    private Type role;

}
