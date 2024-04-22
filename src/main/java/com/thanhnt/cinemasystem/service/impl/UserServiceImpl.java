package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.repository.UserRepository;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.UserService;
import java.util.List;
import java.util.Optional;
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
  public Optional<User> findByEmail(String mail) {
    return userRepository.findByEmail(mail);
  }

  @Override
  public ErrorCode validateSignUp(SignupRequest signupRequest) {
    boolean existPhone = userRepository.existsByPhone(signupRequest.getPhone());
    boolean existMail = userRepository.existsByEmail(signupRequest.getEmail());

    if (existPhone && existMail) {
      return ErrorCode.BOTH_EXIST;
    } else if (!existPhone && existMail) {
      return ErrorCode.EMAIL_EXIST;

    } else if (existPhone && !existMail) {
      return ErrorCode.PHONE_EXIST;
    }
    return null;
  }

  @Override
  @Transactional
  public void signup(User user) {
    User userSave = userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByEmail((mail))
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    List<GrantedAuthority> authorityList =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
            .collect(Collectors.toList());

    return SecurityUserDetails.build(user, authorityList);
  }
}
