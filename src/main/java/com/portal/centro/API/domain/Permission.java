package com.portal.centro.API.domain;

import com.portal.centro.API.enums.PermissionType;
import com.portal.centro.API.generic.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
public class Permission extends IObject {

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Getter
    @Setter
    @Column(name = "type")
    private PermissionType permissionType;
}
