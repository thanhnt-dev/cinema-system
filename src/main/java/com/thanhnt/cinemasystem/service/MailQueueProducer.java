package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;

public interface MailQueueProducer {
  void sendMailMessage(OtpMailDTO mailInfo);
}
