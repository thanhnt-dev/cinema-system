package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.responsitory.UserRepository;
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
