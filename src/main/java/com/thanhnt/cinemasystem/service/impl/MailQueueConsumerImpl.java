package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;
import com.thanhnt.cinemasystem.service.MailQueueConsumer;
import com.thanhnt.cinemasystem.service.MailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailQueueConsumerImpl implements MailQueueConsumer {
  private final MailService mailService;

  private static final Logger LOGGER = LoggerFactory.getLogger(MailQueueConsumerImpl.class);
  @Override
  @RabbitListener(queues = {"${rabbitmq.name}"})
  public void consumeOTPMailMessage(OtpMailDTO otpMailDTO) {
    LOGGER.info("Send OTP to Mail: ");
    mailService.sendMail(otpMailDTO);
  }
}
