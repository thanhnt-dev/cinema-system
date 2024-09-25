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
  PHONE_AND_MAIL_EXIST("1050", "Both phone number and email already exist"),
  EMAIL_EXIST("1051", "Email already exists"),
  PHONE_EXIST("1052", "Phone number already exists"),

  // Login exception
  USER_IS_DEACTIVATED("1100", "Your account is deactivated"),
  BAD_CREDENTIAL_LOGIN("1101", "Invalid username or password"),
  USER_NOT_LOGIN("1102", "You need to log in to change password."),

  // OTP exception
  OTP_INVALID_OR_EXPIRED("1150", "Your Code invalid or expired"),
  OTP_NOT_MATCH("1151", "Your Code does not match"),

  // Password exception
  CURRENT_PASSWORD_DOES_NOT_MATCH("1200", "Current password is invalid"),
  INVALID_CONFIRM_NEW_PASSWORD("1201", "New password and confirm new password does not match"),
  OLD_PASSWORD_EQUALS_NEW_PASSWORD(
      "1202", "Please choose a new password different from the old one"),
  PASSWORD_AND_NEW_PASSWORD_IS_NOT_EXIST("1203", "Please enter password and confirm password"),

  // Address exception
  PROVINCE_NOT_FOUND("1250", "Province is not found."),
  DISTRICT_NOT_FOUND("1251", "District is not found."),
  WARD_NOT_FOUND("1252", "Ward is not found."),
  IMPORT_LOCATION_ERROR("1253", "Fail to parse CSV file."),

  // Token exception
  REFRESH_TOKEN_INVALID("1300", "Invalid refresh token"),

  // UNAUTHORIZED
  API_KEY_INVALID("1350", "Invalid API key."),

  // Movie exception
  MOVIE_NOT_FOUND("1400", "Movie not found."),

  // Movie_time exception
  MOVIE_TIME_NOT_FOUND("1401", "Movie time not found."),

  // Seat exception
  SEAT_HAS_BOOKED("1402", "Ticket has already been booked for this seat and showtime."),
  SEAT_NOT_FOUND("1403", "Ticket is not found."),

  // Discount exception
  DISCOUNT_NOT_FOUND_OR_EXPIRED("1404", "Discount not found, out of quantity or expired."),

  // Order exception
  ORDER_NOT_FOUND("1405", "Order not found."),

  // Movie Genre exception
  MOVIE_GENRE_NOT_FOUNT("1406", "Movie genre not found.");
  private final String code;
  private final String message;
}
