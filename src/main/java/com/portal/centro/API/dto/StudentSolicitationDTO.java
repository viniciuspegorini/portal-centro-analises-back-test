package com.portal.centro.API.dto;

import com.portal.centro.API.model.User;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class StudentSolicitationDTO {

    @NotNull
    private User solicitatedTo;

}
