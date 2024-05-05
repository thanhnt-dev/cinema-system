package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.SignupException;
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
  public void validateSignUp(SignupRequest signupRequest) throws SignupException {
    boolean isExistsByPhoneNumber = userRepository.existsByPhone(signupRequest.getPhone());
    boolean isExistsByEmail = userRepository.existsByEmail(signupRequest.getEmail());

    boolean isPhoneAndEmailExisted = isExistsByPhoneNumber && isExistsByEmail;

    if (isPhoneAndEmailExisted) throw new SignupException(ErrorCode.PHONE_AND_MAIL_EXIST);
    if (isExistsByEmail) throw new SignupException(ErrorCode.EMAIL_EXIST);
    if (isExistsByPhoneNumber) throw new SignupException(ErrorCode.PHONE_EXIST);
  }

  @Override
  @Transactional
  public void signup(User user) {
    userRepository.save(user);
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

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }
}
