package com.thanhnt.cinemasystem.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChangePasswordRequest {
  @NotNull(message = "Old password is require")
  @NotBlank(message = "Old password can not be blank")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
      message =
          "The old password must be lest 8 characters, English, numbers, and special characters.")
  @Schema(description = "old password", example = "NguyenVan@123")
  private String oldPassword;

  @NotNull(message = "New password is require")
  @NotBlank(message = "New password can not be blank")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
      message =
          "The new password must be lest 8 characters, English, numbers, and special characters.")
  @Schema(description = "new password", example = "TranVan@#123")
  private String newPassword;

  @NotNull(message = "Confirm password is require")
  @NotBlank(message = "Confirm password can not be blank")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
      message =
          "The confirm password must be lest 8 characters, English, numbers, and special characters.")
  @Schema(description = "Confirm password", example = "TranVan#123")
  private String confirmNewPassword;
}
