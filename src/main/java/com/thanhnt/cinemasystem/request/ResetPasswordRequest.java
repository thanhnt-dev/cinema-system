package com.thanhnt.cinemasystem.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
  private String email;
  private String password;
  private String confirmPassword;
}
