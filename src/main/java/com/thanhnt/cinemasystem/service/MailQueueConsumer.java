package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;

public interface MailQueueConsumer {
  void consumeOTPMailMessage(OtpMailDTO otpMailDTO);
}
