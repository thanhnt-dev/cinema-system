package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.UserFacade;
import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
  public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return this.userFacade.login(request);
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User Signup")
  public BaseResponse<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
    return this.userFacade.signUp(request);
  }

  @PostMapping("/confirm-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User confirm otp")
  public BaseResponse<Void> confirmOTP(@Valid @RequestBody ConfirmOTPRequest request) {
    this.userFacade.confirmOTP(request);
    return BaseResponse.ok();
  }

  @PostMapping("/resend-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User resend otp")
  public BaseResponse<Void> resendOTP(@Valid @RequestBody OtpMailRequest request) {
    this.userFacade.resendOTP(request);
    return BaseResponse.ok();
  }

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User forgot password")
  public BaseResponse<Void> forgotPassword(@Valid @RequestBody OtpMailRequest request) {
    this.userFacade.forgotPassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"USER APIs"},
      summary = "User reset password")
  public BaseResponse<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
    this.userFacade.resetPassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/change-password")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User change password")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
    this.userFacade.changePassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User logout")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> logout() {
    this.userFacade.logout();
    return BaseResponse.ok();
  }

  @GetMapping("/profile")
  @ResponseStatus(HttpStatus.OK)
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User profile")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<UserProfileResponse> getProfile() {
    return this.userFacade.getProfile();
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("isAuthenticated()")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(
      tags = {"USER APIs"},
      summary = "User update")
  public BaseResponse<UserProfileResponse> updateUser(
      @Valid @RequestBody UpdateUserRequest request) {
    return this.userFacade.updateUser(request);
  }

  @PostMapping("/refresh-token")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Refresh token",
      tags = {"USER APIs"})
  public BaseResponse<NewAccessTokenResponse> refreshToken(RefreshTokenRequest request) {
    return this.userFacade.refreshToken(request);
  }
}
