package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;
import com.thanhnt.cinemasystem.entity.*;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.enums.Gender;
import com.thanhnt.cinemasystem.enums.OTPType;
import com.thanhnt.cinemasystem.enums.RoleUser;
import com.thanhnt.cinemasystem.exception.*;
import com.thanhnt.cinemasystem.facade.UserFacade;
import com.thanhnt.cinemasystem.request.*;
import com.thanhnt.cinemasystem.response.*;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFacadeImpl implements UserFacade {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final MailQueueProducer mailQueueProducer;
  private final UserService userService;
  private final TokenService tokenService;
  private final JWTService jwtService;
  private final RoleService roleService;
  private final CacheService cacheService;
  private final ProvinceService provinceService;
  private final DistrictService districtService;
  private final WardService wardService;

  @Override
  public BaseResponse<LoginResponse> login(LoginRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user =
        userService
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    boolean isNotActive = !user.isActive();
    if (isNotActive) throw new LoginException(ErrorCode.USER_IS_DEACTIVATED);

    if (user.isFirstLogin()) sendOTP(user.getEmail(), OTPType.REGISTER);

    SecurityUserDetails userPrinciple = (SecurityUserDetails) authentication.getPrincipal();
    return BaseResponse.build(buildLoginResponse(userPrinciple, user), true);
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
    userService.signup(user);
    return BaseResponse.build(buildSignupResponse(user), true);
  }

  @Override
  public void confirmOTP(ConfirmOTPRequest request) {
    String cacheKey = String.format("%s-%s", getCacheKey(request.getOtpType()), request.getEmail());
    String cachedValue = (String) cacheService.retrieve(cacheKey);
    if (null == cachedValue) throw new OTPException(ErrorCode.OTP_INVALID_OR_EXPIRED);
    boolean isValidOTP = cachedValue.equals(request.getOtpCode());
    if (!isValidOTP) throw new OTPException(ErrorCode.OTP_NOT_MATCH);

    cacheService.delete(cacheKey);
    if (request.getOtpType().isRegister()) {
      User user =
          userService
              .findByEmail(request.getEmail())
              .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
      user.isLoggedIn();
      userService.saveUser(user);
    }
  }

  @Override
  public void resendOTP(OtpMailRequest request) {
    String cacheKey = String.format("%s-%s", getCacheKey(request.getOtpType()), request.getEmail());

    boolean isKeyExist = cacheService.hasKey(cacheKey);
    if (isKeyExist) cacheService.delete(cacheKey);
    sendOTP(request.getEmail(), request.getOtpType());
  }

  @Override
  public void forgotPassword(OtpMailRequest request) {
    userService
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    String cacheKey = String.format("%s-%s", getCacheKey(request.getOtpType()), request.getEmail());

    boolean isKeyExist = cacheService.hasKey(cacheKey);
    if (isKeyExist) cacheService.delete(cacheKey);
    sendOTP(request.getEmail(), request.getOtpType());
  }

  @Override
  public void resetPassword(ResetPasswordRequest request) {
    User user =
        userService
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    boolean isInvalidRequest =
        request.getNewPassword() == null || request.getConfirmPassword() == null;
    if (isInvalidRequest)
      throw new ChangePasswordException(ErrorCode.PASSWORD_AND_NEW_PASSWORD_IS_NOT_EXIST);

    boolean isValidConfirmPassword = request.getNewPassword().equals(request.getConfirmPassword());
    if (!isValidConfirmPassword)
      throw new ChangePasswordException(ErrorCode.INVALID_CONFIRM_NEW_PASSWORD);

    user.changePassword(passwordEncoder.encode(request.getNewPassword()));
    userService.saveUser(user);
  }

  @Override
  @Transactional
  public void changePassword(ChangePasswordRequest request) {
    var userPrinciple =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        userService
            .findByEmail(userPrinciple.getEmail())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
    boolean isValidCurrentPassword =
        passwordEncoder.matches(request.getOldPassword(), user.getPassword());
    if (!isValidCurrentPassword)
      throw new ChangePasswordException(ErrorCode.CURRENT_PASSWORD_DOES_NOT_MATCH);

    boolean isValidConfirmPassword =
        request.getNewPassword().equals(request.getConfirmNewPassword());
    if (!isValidConfirmPassword)
      throw new ChangePasswordException(ErrorCode.INVALID_CONFIRM_NEW_PASSWORD);

    boolean isPasswordDifferent = request.getOldPassword().equals(request.getNewPassword());
    if (isPasswordDifferent)
      throw new ChangePasswordException(ErrorCode.OLD_PASSWORD_EQUALS_NEW_PASSWORD);

    user.changePassword(passwordEncoder.encode(request.getNewPassword()));
    userService.saveUser(user);
  }

  @Override
  public BaseResponse<UserProfileResponse> getProfile() {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        userService
            .findByEmail(principal.getEmail())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
    ProvinceResponse provinceResponse = provinceResponse(user);
    DistrictResponse districtResponse = districtResponse(user);
    WardResponse wardResponse = wardResponse(user);

    return BaseResponse.build(
        UserProfileResponse.builder()
            .email(user.getEmail())
            .name(user.getName())
            .phone(user.getPhone())
            .gender(user.getGender())
            .dateOfBirth(user.getDateOfBirth())
            .province(provinceResponse)
            .district(districtResponse)
            .ward(wardResponse)
            .build(),
        true);
  }

  @Override
  @Transactional
  public BaseResponse<UserProfileResponse> updateUser(UpdateUserRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        this.userService
            .findById(principal.getId())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
    String name = request.getName();
    Gender gender = request.getGender();
    String phone = request.getPhone();
    boolean isPhoneChanged = !user.getPhone().equals(request.getPhone());
    if (isPhoneChanged) {
      boolean isExistsByPhoneNumber = userService.existByPhone(request.getPhone());
      if (isExistsByPhoneNumber) throw new UpdateUserException(ErrorCode.PHONE_EXIST);
    }
    Long dateOfBirth = request.getDateOfBirth();
    Province province = getProvince(request.getProvinceID());
    District district = getDistrict(request.getDistrictID());
    Ward ward = getWard(request.getWardID());
    user.updateProfile(name, gender, phone, dateOfBirth, province, district, ward);
    userService.updateUser(user);
    ProvinceResponse provinceResponse = provinceResponse(user);
    DistrictResponse districtResponse = districtResponse(user);
    WardResponse wardResponse = wardResponse(user);
    return BaseResponse.build(
        UserProfileResponse.builder()
            .email(user.getEmail())
            .name(user.getName())
            .phone(user.getPhone())
            .gender(user.getGender())
            .dateOfBirth(user.getDateOfBirth())
            .province(provinceResponse)
            .district(districtResponse)
            .ward(wardResponse)
            .build(),
        true);
  }

  @Override
  public BaseResponse<NewAccessTokenResponse> refreshToken(RefreshTokenRequest request) {
    RefreshToken refreshToken = this.tokenService.validateRefreshToken(request.getRefreshToken());
    User user =
        this.userService
            .findById(refreshToken.getUser().getId())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
    List<GrantedAuthority> authorityList =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
            .collect(Collectors.toList());
    SecurityUserDetails userDetails = SecurityUserDetails.build(user, authorityList);
    String newAccessToken = this.jwtService.generateAccessToken(userDetails);
    String newRefreshToken = this.jwtService.generateRefreshToken(userDetails);
    refreshToken.updateNewRefreshToken(refreshToken.getRefreshToken());
    tokenService.updateRefreshToken(refreshToken);
    return BaseResponse.build(
        NewAccessTokenResponse.builder()
            .accessToken(newAccessToken)
            .refreshToken(newRefreshToken)
            .build(),
        true);
  }

  @Override
  public void logout() {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        userService
            .findById(principal.getId())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));
    tokenService.deleteRefreshToken(user.getId());
  }

  private LoginResponse buildLoginResponse(SecurityUserDetails userDetails, User user) {
    var accessToken = jwtService.generateAccessToken(userDetails);
    var refreshToken = jwtService.generateRefreshToken(userDetails);

    List<RoleUser> roleUsers = user.getRoles().stream().map(Role::getName).toList();
    tokenService.saveRefreshToken(refreshToken, user);
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
    String cacheKey = String.format("%s-%s", getCacheKey(otpType), receiverMail);

    cacheService.store(cacheKey, otp, 5, TimeUnit.MINUTES);
    mailQueueProducer.sendMailMessage(
        OtpMailDTO.builder()
            .receiverMail(receiverMail)
            .otpCode(otp)
            .type(OTPType.REGISTER)
            .build());
  }

  private String getCacheKey(OTPType otpType) {
    return otpType.isRegister()
        ? String.format("%s", "Register")
        : String.format("%s", "ForgotPassword");
  }

  private Province getProvince(Long id) {
    return provinceService
        .findProvinceById(id)
        .orElseThrow(() -> new LocationException(ErrorCode.PROVINCE_NOT_FOUND));
  }

  private ProvinceResponse provinceResponse(User user) {
    return ProvinceResponse.builder()
        .id(user.getProvince().getId())
        .provinceName(user.getProvince().getProvinceName())
        .build();
  }

  private District getDistrict(Long id) {
    return districtService
        .findDistrictById(id)
        .orElseThrow(() -> new LocationException(ErrorCode.DISTRICT_NOT_FOUND));
  }

  private DistrictResponse districtResponse(User user) {
    return DistrictResponse.builder()
        .id(user.getDistrict().getId())
        .districtName(user.getDistrict().getDistrictName())
        .build();
  }

  private Ward getWard(Long id) {
    return wardService
        .findWardById(id)
        .orElseThrow(() -> new LocationException(ErrorCode.WARD_NOT_FOUND));
  }

  private WardResponse wardResponse(User user) {
    return WardResponse.builder()
        .id(user.getWard().getId())
        .wardName(user.getWard().getWardName())
        .build();
  }
}
