package com.portal.centro.API.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePasswordDTO {

  @NotNull
  @NotBlank
  private String oldPassword;

  @NotNull
  @NotBlank
  private String newPassword;

}
