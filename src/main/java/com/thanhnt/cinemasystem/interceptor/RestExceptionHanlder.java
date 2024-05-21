package com.thanhnt.cinemasystem.interceptor;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.*;
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

  @ExceptionHandler(OTPException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleOTPException(
      OTPException otpException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(otpException.getErrorCode(), otpException.getMessage()), false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidTokenException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleOTPException(
      InvalidTokenException invalidTokenException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(
                invalidTokenException.getErrorCode(), invalidTokenException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RoleException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleOTPException(
      RoleException roleException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(roleException.getErrorCode(), roleException.getMessage()), false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ChangePasswordException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleOTPException(
      ChangePasswordException changePasswordException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(
                changePasswordException.getErrorCode(), changePasswordException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UpdateUserException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ResponseEntity<BaseResponse<ExceptionResponse>> handleOTPException(
      UpdateUserException UpdateUserException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(
                UpdateUserException.getErrorCode(), UpdateUserException.getMessage()),
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

  @ExceptionHandler(ImportException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleImportException(
      ImportException importException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(importException.getErrorCode(), importException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LocationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleLocationException(
      LocationException locationException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(locationException.getErrorCode(), locationException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleUnauthorizedException(
      UnauthorizedException unauthorizedException) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(
                unauthorizedException.getErrorCode(), unauthorizedException.getMessage()),
            false),
        HttpStatus.BAD_REQUEST);
  }
}
