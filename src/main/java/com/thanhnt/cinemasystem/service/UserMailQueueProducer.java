package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;

public interface UserMailQueueProducer {
  void sendMailMessage(OTPMailDTO mailInfo);
}
