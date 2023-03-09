package com.portal.centro.API.domain;

import com.portal.centro.API.enums.PermissionType;
import com.portal.centro.API.generic.IObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
public class Permission extends IObject {

    @Getter
    @Setter
    @Column(name = "description")
    String description;

    @Getter
    @Setter
    @Column(name = "type")
    PermissionType permissionType;
}
