package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.District;
import com.thanhnt.cinemasystem.entity.Province;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.entity.Ward;
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

  Optional<Province> findProvinceById(Long id);

  Optional<District> findDistrictById(Long id);

  Optional<Ward> findWardById(Long id);

  boolean existByPhone(String phone);
}
