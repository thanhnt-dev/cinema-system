package com.thanhnt.cinemasystem.exception;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SeatException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public SeatException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
