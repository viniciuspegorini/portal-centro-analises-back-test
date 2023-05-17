package com.portal.centro.API.model;

import com.portal.centro.API.enums.TypeUser;
import com.portal.centro.API.generic.base.IModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "solicitation")
public class Solicitation extends IModel {

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeUser typeUser;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull
    private String content;

    private String comments;

    @Size(max = 255)
    @Column(name = "analysis_specification")
    private String analysisSpecification;

    @NotNull
    @Column(name = "amount_samples")
    private Integer amountSamples;

}
