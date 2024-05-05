package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;

public interface UserFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<SignupResponse> signUp(SignupRequest request);

  void confirmOTP(ConfirmOTPRequest confirmOTPRequest);

  void resendOTP(OtpMailRequest otpMailRequest);

  void forgotPassword(OtpMailRequest otpMailRequest);

  void resetPassword(ResetPasswordRequest resetPasswordRequest);

  void changePassword(ChangePasswordRequest changePasswordRequest);
}
