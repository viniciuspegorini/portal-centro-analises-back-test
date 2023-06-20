package com.portal.centro.API.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordDTO {

  @NotNull
  @NotBlank
  @NotEmpty
  @Size(min = 6, max = 254, message = "Old Password must be between 6 and 254 characters long")
  private String oldPassword;

  @NotNull
  @NotBlank
  @NotEmpty
  @Size(min = 6, max = 254, message = "New Password must be between 6 and 254 characters long")
  private String newPassword;

}
