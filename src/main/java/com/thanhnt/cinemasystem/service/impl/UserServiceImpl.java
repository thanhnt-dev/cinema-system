package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.reponsitory.UserRepository;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.SignupResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User findByEmail(String mail) {
    return userRepository
        .findByEmail(mail)
        .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
  }

  @Override
  public boolean validSignup(SignupRequest signupRequest) {
    return userRepository.existsByPhone(signupRequest.getPhone())
        && userRepository.existsByEmail(signupRequest.getEmail());
  }

  @Override
  @Transactional
  public SignupResponse signup(User user) {
    User userSave = userRepository.save(user);
    return new SignupResponse(userSave.getEmail(), userSave.getPhone());
  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByEmail((mail))
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    List<GrantedAuthority> authorityList =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

    return SecurityUserDetails.build(user, authorityList);
  }
}
