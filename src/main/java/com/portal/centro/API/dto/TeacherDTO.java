package com.portal.centro.API.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherDTO {
    Long id;
    String nome;
    String email;
}
