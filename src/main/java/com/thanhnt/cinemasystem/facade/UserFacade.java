package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.*;

public interface UserFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<SignupResponse> signUp(SignupRequest request);

  BaseResponse<UserProfileResponse> getProfile();

  BaseResponse<UserProfileResponse> updateUser(UpdateUserRequest request);

  BaseResponse<NewAccessTokenResponse> refreshToken(RefreshTokenRequest request);

  void confirmOTP(ConfirmOTPRequest request);

  void resendOTP(OtpMailRequest request);

  void forgotPassword(OtpMailRequest request);

  void resetPassword(ResetPasswordRequest request);

  void changePassword(ChangePasswordRequest request);

  void logout();
}
