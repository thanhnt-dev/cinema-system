package com.thanhnt.cinemasystem.enums;

public enum OTPType {
  REGISTER,
  FORGET_PASSWORD;

  public boolean isRegister() {
    return this == REGISTER;
  }

  public boolean isForgetPassword() {
    return this == FORGET_PASSWORD;
  }
}
