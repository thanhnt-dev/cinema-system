package com.thanhnt.cinemasystem.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginRequest {
  @NotNull(message = "Email is require")
  @NotBlank(message = "Email can not be blank")
  @Pattern(
      regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
      message = "The email format is incorrect.")
  @Schema(description = "example", example = "example@email.com")
  private String email;

  @NotNull(message = "Password is require")
  @NotBlank(message = "Password can not be blank")
  private String password;
}
