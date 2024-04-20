package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import org.springframework.security.core.Authentication;

public interface JWTService {
  LoginResponse generateToken(Authentication authentication);

  LoginResponse generateToken(SecurityUserDetails principal);

  Boolean validateToken(String token);

  Long getIdFromJwtToken(String token);

  String getEmailFromJwtToken(String token);
}
