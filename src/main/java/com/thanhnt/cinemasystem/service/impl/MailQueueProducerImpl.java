package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;
import com.thanhnt.cinemasystem.service.MailQueueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailQueueProducerImpl implements MailQueueProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.exchangeName}")
  private String exchange;

  @Value("${rabbitmq.mailRoutingKey}")
  private String messageRoutingKey;

  @Override
  public void sendMailMessage(OTPMailDTO mailInfo) {
    rabbitTemplate.convertAndSend(exchange, messageRoutingKey, mailInfo);
  }
}
