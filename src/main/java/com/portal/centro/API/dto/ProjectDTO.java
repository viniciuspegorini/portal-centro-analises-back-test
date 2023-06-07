package com.portal.centro.API.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class ProjectDTO {

    private String description;

    private String subject;

    private Long id;

}
