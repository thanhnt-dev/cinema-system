package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;

public interface UserFace {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<SignupResponse> signup(SignupRequest request);
}
