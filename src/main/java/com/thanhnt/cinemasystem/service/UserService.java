package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.SignupResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User findByEmail(String mail);

  boolean validSignup(SignupRequest signupRequest);

  SignupResponse signup(User user);
}
