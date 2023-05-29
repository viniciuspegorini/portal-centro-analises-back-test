package com.portal.centro.API.model;

import com.portal.centro.API.generic.base.IModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Setter
@Getter
public class MultiPartFileList extends IModel {

    private String fileName;

    private String contentType;

    @ManyToOne
    private TechnicalReport technicalReport;
}
