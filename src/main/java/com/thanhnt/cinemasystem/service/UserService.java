package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.request.SignupRequest;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  Optional<User> findByEmail(String mail);

  void validateSignUp(SignupRequest signupRequest);

  void signup(User user);

  void saveUser(User user);
}
