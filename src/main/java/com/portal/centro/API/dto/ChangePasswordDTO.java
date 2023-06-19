package com.portal.centro.API.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordDTO {

  @NotNull
  private String oldPassword;
  @NotNull
  private String newPassword;

}
