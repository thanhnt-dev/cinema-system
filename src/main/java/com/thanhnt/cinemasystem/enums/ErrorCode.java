package com.thanhnt.cinemasystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // User exception
  SIGNUP_IS_FAILED("1000", "Signup not successful"),
  USER_NOT_FOUND("1001", "Can not find user with email"),

  // Role exception
  ROLE_NOT_FOUND("1002", "Role not found"),

  // sign up
  PHONE_AND_MAIL_EXIST("1100", "Both phone number and email already exist"),
  EMAIL_EXIST("1101", "Email already exists"),
  PHONE_EXIST("1102", "Phone number already exists"),

  // Login exceptioin
  USER_IS_DEACTIVATED("1200", "Your account is deactivated"),
  BAD_CREDENTIAL_LOGIN("1201", "Invalid username or password");

  private final String code;
  private final String message;
}
