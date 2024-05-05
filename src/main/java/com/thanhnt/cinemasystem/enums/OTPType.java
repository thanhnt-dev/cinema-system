package com.thanhnt.cinemasystem.enums;

public enum OTPType {
  REGISTER,
  FORGOT_PASSWORD;

  public boolean isRegister() {
    return this == REGISTER;
  }

  public boolean isForgotPassword() {
    return this == FORGOT_PASSWORD;
  }
}
