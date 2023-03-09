package com.portal.centro.API.domain;

import com.portal.centro.API.generic.IObject;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
public class Permission extends IObject {

    @NotBlank
    @Getter
    @Setter
    private String description;
}
