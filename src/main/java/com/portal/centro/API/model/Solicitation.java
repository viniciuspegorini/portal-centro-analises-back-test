package com.portal.centro.API.model;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.enums.TypeUser;
import com.portal.centro.API.generic.base.IModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "solicitation")
public class Solicitation extends IModel {

    @NotNull(message = "Equipment must not be null")
    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull(message = "Project must not be null")
    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotNull(message = "Description must not be null")
    @NotBlank(message = "Description must not be empty")
    private String description;

    @NotBlank(message = "Fields must not be blank")
    private String fields;

    @Enumerated
    private SolicitationStatus status;

    @Enumerated(value = EnumType.STRING)
    private TypeUser typeUser;

}
