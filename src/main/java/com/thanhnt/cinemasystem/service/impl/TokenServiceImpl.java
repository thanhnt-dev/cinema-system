package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.RefreshToken;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.repository.TokenRepository;
import com.thanhnt.cinemasystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final TokenRepository tokenRepository;

  @Override
  public void saveRefreshToken(String refreshToken, User user) {
    RefreshToken token = RefreshToken.builder().user(user).refreshToken(refreshToken).build();
    tokenRepository.save(token);
  }

  @Override
  @Transactional
  public void deleteRefreshToken(Long id) {
    this.tokenRepository.deleteByUserId(id);
  }
}