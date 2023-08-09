package com.portal.centro.API.model;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.generic.base.IModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Audit extends IModel {

    @OneToOne
    private Solicitation solicitation;

    @OneToOne
    private User updatedBy;

    @Enumerated
    private SolicitationStatus newStatus;

    private LocalDateTime changeDate;
}


