package com.thanhnt.cinemasystem.dto;

import com.thanhnt.cinemasystem.enums.OTPType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OtpMailDTO {
  private String receiverMail;
  private String otpCode;
  private OTPType type;
}
