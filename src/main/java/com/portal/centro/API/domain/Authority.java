package com.portal.centro.API.domain;

import com.portal.centro.API.generic.IModel;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class Authority extends IModel implements GrantedAuthority {

    @Column(length = 40, nullable = false)
    private String authority;
}