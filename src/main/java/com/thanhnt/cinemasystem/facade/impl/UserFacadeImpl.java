package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;
import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.enums.OTPType;
import com.thanhnt.cinemasystem.enums.RoleUser;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.OTPException;
import com.thanhnt.cinemasystem.exception.SignupException;
import com.thanhnt.cinemasystem.facade.UserFacade;
import com.thanhnt.cinemasystem.request.ConfirmOTPRequest;
import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.request.OtpMailRequest;
import com.thanhnt.cinemasystem.request.SignupRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.LoginResponse;
import com.thanhnt.cinemasystem.response.SignupResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
  private final PasswordEncoder passwordEncoder;
  private final MailQueueProducer mailQueueProducer;
  private final UserService userService;
  private final JWTService jwtService;
  private final RoleService roleService;
  private final CacheService cacheService;

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

    Role userRole =
        roleService
            .findRole(RoleUser.ROLE_USER)
            .orElseThrow(() -> new SignupException(ErrorCode.ROLE_NOT_FOUND));

    user.addRole(userRole);
    if (user.isFirstLogin()) sendOTP(user.getEmail(), OTPType.REGISTER);

    userService.signup(user);
    return BaseResponse.build(buildSignupResponse(user), true);
  }

  @Override
  public void confirmOTP(ConfirmOTPRequest confirmOTPRequest) {
    String cacheKey =
        confirmOTPRequest.getOtpType().isRegister()
            ? String.format("%s-%s", "Register", confirmOTPRequest.getEmail())
            : String.format("%s-%s", "ForgetPassword", confirmOTPRequest.getEmail());
    String cachedValue = (String) cacheService.retrieve(cacheKey);
    if (null == cachedValue) throw new OTPException(ErrorCode.OTP_INVALID_OR_EXPIRED);
    boolean isValidOTP = cachedValue.equals(confirmOTPRequest.getOtpCode());
    if (!isValidOTP) throw new OTPException(ErrorCode.OTP_NOT_MATCH);

    cacheService.delete(cacheKey);
    if (confirmOTPRequest.getOtpType().isRegister()) {
      User user =
          userService
              .findByEmail(confirmOTPRequest.getEmail())
              .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
      user.isLoggedIn();
      userService.saveUser(user);
    }
  }

  @Override
  public void resendOTP(OtpMailRequest otpMailRequest) {
    sendOTP(otpMailRequest.getEmail(), otpMailRequest.getOtpType());
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
    return SignupResponse.builder().email(user.getEmail()).build();
  }

  private String generateOtp() {
    Random random = new Random();
    int otp = random.nextInt(999999);
    return String.format("%06d", otp);
  }

  private void sendOTP(String receiverMail, OTPType otpType) {
    String otp = generateOtp();
    String cacheKey =
            otpType.isRegister()
            ? String.format("%s-%s", "Register", receiverMail)
            : String.format("%s-%s", "ForgotPassword", receiverMail);
    cacheService.store(cacheKey, otp, 5, TimeUnit.MINUTES);
    mailQueueProducer.sendMailMessage(
        OtpMailDTO.builder()
            .receiverMail(receiverMail)
            .otpCode(otp)
            .type(OTPType.REGISTER)
            .build());
  }

}
