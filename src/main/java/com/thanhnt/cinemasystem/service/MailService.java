package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;

public interface MailService {
  void sendMail(OtpMailDTO otpMailDTO);
}
