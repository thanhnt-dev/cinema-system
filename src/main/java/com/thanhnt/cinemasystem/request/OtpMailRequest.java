package com.thanhnt.cinemasystem.request;

import com.thanhnt.cinemasystem.enums.OTPType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OtpMailRequest {
    private String email;
    private OTPType otpType;
}
