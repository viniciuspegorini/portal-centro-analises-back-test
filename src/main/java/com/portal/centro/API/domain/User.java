package com.portal.centro.API.domain;

import com.portal.centro.API.generic.IModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String username;

    @Column(name = "password")
    @Getter
    @Setter
    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    @Column(name = "email")
    @NotNull
    @Email
    @Getter
    @Setter
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
