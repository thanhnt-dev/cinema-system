package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.request.SignupRequest;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  Optional<User> findByEmail(String mail);

  Optional<User> findById(Long id);

  void validateSignUp(SignupRequest signupRequest);

  void signup(User user);

  void saveUser(User user);

  void updateUser(User user);

  boolean existByPhone(String phone);
}
