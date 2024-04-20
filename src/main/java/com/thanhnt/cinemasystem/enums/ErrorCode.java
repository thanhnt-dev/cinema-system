package com.thanhnt.cinemasystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  USER_NOT_FOUND("1002", "Can not find user with email");

  private final String code;
  private final String message;
}
