package com.portal.centro.API.domain;

import com.portal.centro.API.enums.RoleType;
import com.portal.centro.API.enums.FormType;
import com.portal.centro.API.generic.IModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "form")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Form extends IModel {

    @Column(name = "content")
    @Getter
    @Setter
    @Size(min = 6, max = 20)
    private String content;

    @Column(name = "subject")
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 100)
    private String subject;

    @Column(name = "type")
    @NotNull
    @Enumerated
    @Getter
    @Setter
    private FormType type;

    @Column(name = "role")
    @Enumerated
    @Setter
    private RoleType role;
}
