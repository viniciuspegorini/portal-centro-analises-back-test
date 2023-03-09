package com.portal.centro.API.domain;

import com.portal.centro.API.generic.IObject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity(name = "role")
public class Role extends IObject {

    @Getter
    @Setter
    @NotBlank
    private String description;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<User> user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Permission> permission;
}
