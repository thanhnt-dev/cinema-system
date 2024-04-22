package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.enums.RoleUser;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.facade.UserFacade;
import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.JWTService;
import com.thanhnt.cinemasystem.service.RoleService;
import com.thanhnt.cinemasystem.service.UserService;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacadeImpl implements UserFacade {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JWTService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;

  @Override
  public BaseResponse<LoginResponse> login(LoginRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Optional<User> user = userService.findByEmail(request.getEmail());

    boolean isActive = user.isPresent();
    if (!isActive) throw new LoginException(ErrorCode.USER_IS_DEACTIVATED);

    SecurityUserDetails userPrinciple = (SecurityUserDetails) authentication.getPrincipal();
    return BaseResponse.build(buildLoginResponse(userPrinciple, user.get()), true);
  }

  @Override
  public BaseResponse<SignupResponse> signUp(SignupRequest request) {
    userService.validateSignUp(request);

    User user =
        User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .name(request.getName())
            .phone(request.getPhone())
            .dateOfBirth(request.getDateOfBirth())
            .build();

    Optional<Role> userRole = roleService.findRole(RoleUser.ROLE_USER);

    user.addRole(userRole);

    userService.signup(user);
    return BaseResponse.build(buildSignupResponse(user), true);
  }

  private LoginResponse buildLoginResponse(SecurityUserDetails userDetails, User user) {
    var accessToken = jwtService.generateAccessToken(userDetails);
    var refreshToken = jwtService.generateRefreshToken(userDetails);

    List<RoleUser> roleUsers = user.getRoles().stream().map(Role::getName).toList();

    return LoginResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .phone(user.getPhone())
        .name(user.getName())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .roleUsers(roleUsers)
        .build();
  }

  private SignupResponse buildSignupResponse(User user) {
    SignupResponse signupResponse = new SignupResponse();
    SignupResponse.builder().email(user.getEmail()).build();
    return signupResponse;
  }
}
