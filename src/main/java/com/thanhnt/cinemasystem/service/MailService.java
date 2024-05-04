package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;

public interface MailService {
  void sendMail(OTPMailDTO otpMailDTO);
}
