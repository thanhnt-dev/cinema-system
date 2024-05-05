package com.thanhnt.cinemasystem.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChangePasswordRequest {
  private String oldPassword;
  private String newPassword;
  private String confirmNewPassword;
}
