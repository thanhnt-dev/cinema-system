package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import com.thanhnt.cinemasystem.response.UserProfileResponse;

public interface UserFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<SignupResponse> signUp(SignupRequest request);

  BaseResponse<UserProfileResponse> getProfile();

  void confirmOTP(ConfirmOTPRequest request);

  void resendOTP(OtpMailRequest request);

  void forgotPassword(OtpMailRequest request);

  void resetPassword(ResetPasswordRequest request);

  void changePassword(ChangePasswordRequest request);

  BaseResponse<UserProfileResponse> updateUser(UpdateUserRequest request);
}
