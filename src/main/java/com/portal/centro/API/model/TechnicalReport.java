package com.portal.centro.API.model;

import com.portal.centro.API.generic.base.IModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalReport extends IModel {

    @NotNull(message = "Parameter description is required.")
    private String description;

    @NotNull
    private LocalDateTime date;

    /*
    @ManyToOne
    private Solicitation solicitation;
    */

    @NotNull(message = "Parameter price is required.")
    private Float price;

    @NotNull(message = "Parameter amountHours is required.")
    private Integer amountHours;

    @NotNull(message = "Parameter amountSamples is required.")
    private Integer amountSamples;

}
