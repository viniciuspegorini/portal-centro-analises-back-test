package com.portal.centro.API.domain;

import com.portal.centro.API.enums.PermissionType;
import com.portal.centro.API.generic.IModel;
import com.portal.centro.API.generic.IObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "permission")
public class Permission extends IModel {

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
