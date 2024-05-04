package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;
import com.thanhnt.cinemasystem.service.MailQueueConsumer;
import com.thanhnt.cinemasystem.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailQueueConsumerImpl implements MailQueueConsumer {
  private final MailService mailService;

  @Override
  @RabbitListener(queues = {"${rabbitmq.name}"})
  public void consumeOTPMailMessage(OTPMailDTO otpMailDTO) {
    mailService.sendMail(otpMailDTO);
  }
}
