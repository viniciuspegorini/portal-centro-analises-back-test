package com.portal.centro.API.domain;

import com.portal.centro.API.enums.Role;
import com.portal.centro.API.enums.Type;
import com.portal.centro.API.generic.IModel;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    String content;

    @Column(name = "subject")
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 100)
    String subject;


    @Column(name = "type")
    @NotNull
    @Enumerated
    @Getter
    @Setter
    Type type;

    @Column(name = "role")
    @Enumerated
    @Setter
    private Role role;
}
