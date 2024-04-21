package com.thanhnt.cinemasystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // User exception
  SIGNUP_IS_FAILED("1000", "Signup not successful"),
  ROLE_NOT_FOUND("1001", "Role not found"),
  USER_NOT_FOUND("1002", "Can not find user with email"),
  ACCOUNT_IS_DEACTIVATED("1003", "Your account is deactivated"),
  BAD_CREDENTIAL_LOGIN("1005", "Invalid username or password");
  private final String code;
  private final String message;
}
