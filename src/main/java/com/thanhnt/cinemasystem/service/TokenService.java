package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.User;

public interface TokenService {
  void saveRefreshToken(String refreshToken, User user);

  void deleteRefreshToken(Long id);
}
