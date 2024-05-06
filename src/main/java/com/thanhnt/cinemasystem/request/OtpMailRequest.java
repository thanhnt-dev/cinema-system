package com.thanhnt.cinemasystem.request;

import com.thanhnt.cinemasystem.enums.OTPType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OtpMailRequest {
  @NotNull(message = "Email is require")
  @NotBlank(message = "Email can not be blank")
  @Pattern(
      regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
      message = "The email format is incorrect.")
  @Schema(description = "example", example = "example@email.com")
  private String email;

  @NotNull(message = "Password is require")
  @NotBlank(message = "Password can not be blank")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
      message = "The password must be lest 8 characters, English, numbers, and special characters.")
  @Schema(description = "password", example = "NguyenVan@123")
  private OTPType otpType;
}
