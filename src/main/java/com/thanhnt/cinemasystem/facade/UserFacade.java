package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.enums.OTPType;
import com.thanhnt.cinemasystem.request.ConfirmOTPRequest;
import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.OtpMailRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;

public interface UserFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<SignupResponse> signUp(SignupRequest request);

  void confirmOTP(ConfirmOTPRequest confirmOTPRequest);

  void resendOTP(OtpMailRequest otpMailRequest);
}
