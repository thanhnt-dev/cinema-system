package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.UserFace;
import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {

  private final UserFace userFace;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    return this.userFace.login(request);
  }

  @PostMapping("/signup")
  public BaseResponse<SignupResponse> signup(@RequestBody SignupRequest request) {
    return this.userFace.signup(request);
  }
}
