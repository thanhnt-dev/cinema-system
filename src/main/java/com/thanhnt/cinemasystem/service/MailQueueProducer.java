package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;

public interface MailQueueProducer {
  void sendMailMessage(OTPMailDTO mailInfo);
}
