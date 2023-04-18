package com.portal.centro.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.centro.API.enums.Action;
import com.portal.centro.API.generic.base.IModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "permission")
@Entity
public class Permission extends IModel implements GrantedAuthority {

    @Column(name = "description", length = 50, nullable = false)
    @JsonIgnore
    @Setter
    @Getter
    @NotNull(message = "Description must not be null!")
    @NotBlank(message = "Description must not be empty!")
    private String description;

    @Enumerated
    @Getter
    @NotNull(message = "Action must not be null!")
    @NotBlank(message = "Action must not be empty!")
    private Action action;

    @Override
    public String getAuthority() {
        return description;
    }
}
