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

  // Login exception
  USER_IS_DEACTIVATED("1200", "Your account is deactivated"),
  BAD_CREDENTIAL_LOGIN("1201", "Invalid username or password"),
  USER_NOT_LOGGIN("1202", "You need to log in to change password."),

  // OTP exception
  OTP_INVALID_OR_EXPIRED("1300", "Your Code invalid or expired"),
  OTP_NOT_MATCH("1301", "Your Code does not match"),

  // Password exception
  CURRENT_PASSWORD_DOES_NOT_MATCH("1400", "Current password is invalid"),
  INVALID_CONFIRM_NEW_PASSWORD("1401", "New password and confirm new password does not match"),
  OLD_PASSWORD_EQUALS_NEW_PASSWORD(
      "1402", "Please choose a new password different from the old one"),
  PASSWORD_AND_NEW_PASSWORD_IS_NOT_EXIST("1403", "Please enter password and confirm password"),

  // Address exception
  PROVINCE_NOT_FOUND("1500", "Province is not found."),
  DISTRICT_NOT_FOUND("1501", "District is not found."),
  WARD_NOT_FOUND("1502", "Ward is not found."),
  IMPORT_LOCATION_ERROR("1503", "Fail to parse CSV file.");
  private final String code;
  private final String message;
}
