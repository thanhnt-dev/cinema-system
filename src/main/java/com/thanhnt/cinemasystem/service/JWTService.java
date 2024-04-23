package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.security.SecurityUserDetails;

public interface JWTService {

  Boolean validateToken(String token);

  Long getIdFromJwtToken(String token);

  String getEmailFromJwtToken(String token);

  String generateAccessToken(SecurityUserDetails userDetails);

  String generateRefreshToken(SecurityUserDetails userDetails);
}
