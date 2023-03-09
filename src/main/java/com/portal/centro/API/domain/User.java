package com.portal.centro.API.domain;

import com.portal.centro.API.enums.Role;
import com.portal.centro.API.generic.IModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "_user")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "setuniquename", columnNames = "username"),
        @UniqueConstraint(name = "setuniqueemail", columnNames = "email")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends IModel {

    @Column(name = "username")
    @Getter
    @Setter
    @Size(min = 6, max = 20)
    String username;

    @Column(name = "password")
    @Getter
    @Setter
    @NotNull
    @Size(min = 8, max = 100)
    String password;


    @Column(name = "email")
    @NotNull
    @Email
    @Getter
    @Setter
    String email;

    @Column(name = "role")
    @Enumerated
    @Setter
    private Role role;
}
