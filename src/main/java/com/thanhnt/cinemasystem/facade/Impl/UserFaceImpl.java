package com.thanhnt.cinemasystem.facade.Impl;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.enums.RoleUser;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.SignupException;
import com.thanhnt.cinemasystem.facade.UserFace;
import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.JWTService;
import com.thanhnt.cinemasystem.service.UserService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFaceImpl implements UserFace {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JWTService jwtService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public BaseResponse<LoginResponse> login(LoginRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user = userService.findByEmail(request.getEmail());

    boolean isActive = user.isActive();
    if (!isActive) {
      throw new LoginException(ErrorCode.ACCOUNT_IS_DEACTIVATED);
    }
    SecurityUserDetails userPrinciple = (SecurityUserDetails) authentication.getPrincipal();
    return BaseResponse.build(buildLoginResponse(userPrinciple, user), true);
  }

  @Override
  public BaseResponse<SignupResponse> signup(SignupRequest request) {
    if (userService.validSignup(request)) {
      throw new SignupException(ErrorCode.SIGNUP_IS_FAILED);
    }
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setName(request.getName());
    user.setPhone(request.getPhone());
    user.setDateOfBirth(request.getDateOfBirth());
    userService.signup(user);
    return BaseResponse.build(buildSignupResponse(user), true);
  }

  private LoginResponse buildLoginResponse(SecurityUserDetails userDetails, User user) {
    LoginResponse loginResponse = jwtService.generateToken(userDetails);
    loginResponse.setId(user.getId());
    loginResponse.setEmail(user.getEmail());
    loginResponse.setPhone(user.getPhone());
    loginResponse.setName(user.getName());
    loginResponse.setRoleUsers(
        user.getRoles().stream()
            .map(Role::getName)
            .map(RoleUser::getRoleName)
            .collect(Collectors.toList()));
    return loginResponse;
  }

  private SignupResponse buildSignupResponse(User user) {
    SignupResponse signupResponse = new SignupResponse();
    signupResponse.setEmail(user.getEmail());
    signupResponse.setPhone(user.getPhone());
    return signupResponse;
  }
}
