package com.portal.centro.API.domain;

import com.portal.centro.API.generic.IModel;
import com.portal.centro.API.generic.IObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "role")
public class Role extends IModel {

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
