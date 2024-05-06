package com.thanhnt.cinemasystem.exception;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NullException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public NullException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
