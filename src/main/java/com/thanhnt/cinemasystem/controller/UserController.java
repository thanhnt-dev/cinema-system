package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.UserFacade;
import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserFacade userFacade;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Login")
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    return this.userFacade.login(request);
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<SignupResponse> signup(@RequestBody SignupRequest request) {
    return this.userFacade.signUp(request);
  }

  @PostMapping("/confirm-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> confirmOTP(@RequestBody ConfirmOTPRequest request) {
    this.userFacade.confirmOTP(request);
    return BaseResponse.ok();
  }

  @PostMapping("/resend-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> resendOTP(@RequestBody OtpMailRequest request) {
    this.userFacade.resendOTP(request);
    return BaseResponse.ok();
  }

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> forgotPassword(@RequestBody OtpMailRequest request) {
    this.userFacade.forgotPassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
    this.userFacade.resetPassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/change-password")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> changePassword(@RequestBody ChangePasswordRequest request) {
    this.userFacade.changePassword(request);
    return BaseResponse.ok();
  }
}
