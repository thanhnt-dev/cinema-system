package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;

public interface MailQueueConsumer {
  void consumeOTPMailMessage(OTPMailDTO otpMailDTO);
}
