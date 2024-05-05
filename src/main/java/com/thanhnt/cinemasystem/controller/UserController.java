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
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    return this.userFacade.login(loginRequest);
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
    return this.userFacade.signUp(signupRequest);
  }

  @PostMapping("/confirm-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> confirmOTP(@RequestBody ConfirmOTPRequest confirmOTPRequest) {
    this.userFacade.confirmOTP(confirmOTPRequest);
    return BaseResponse.ok();
  }

  @PostMapping("/resend-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> resendOTP(@RequestBody OtpMailRequest otpMailRequest) {
    this.userFacade.resendOTP(otpMailRequest);
    return BaseResponse.ok();
  }

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> forgotPassword(@RequestBody OtpMailRequest otpMailRequest) {
    this.userFacade.forgotPassword(otpMailRequest);
    return BaseResponse.ok();
  }

  @PostMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
    this.userFacade.resetPassword(resetPasswordRequest);
    return BaseResponse.ok();
  }

  @PostMapping("/change-password")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<Void> changePassword(
      @RequestBody ChangePasswordRequest changePasswordRequest) {
    this.userFacade.changePassword(changePasswordRequest);
    return BaseResponse.ok();
  }
}
