package com.thanhnt.cinemasystem.interceptor;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.SignupException;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHanlder extends ResponseEntityExceptionHandler {

  @ExceptionHandler(LoginException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleLoginException(
      LoginException loginException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(loginException.getErrorCode(), loginException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SignupException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleSignException(
      SignupException signupException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(signupException.getErrorCode(), signupException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleBadCredentialsException(
      BadCredentialsException exception) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(
                ErrorCode.BAD_CREDENTIAL_LOGIN.getCode(),
                ErrorCode.BAD_CREDENTIAL_LOGIN.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }
}
