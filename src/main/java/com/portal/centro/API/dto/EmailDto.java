package com.portal.centro.API.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class EmailDto {
    @Email
    private String emailTo;

    @Null
    private String subject;
    @Null
    private String subjectBody;
    @Null
    private String contentBody;
}
