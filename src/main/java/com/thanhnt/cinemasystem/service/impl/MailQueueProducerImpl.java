package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.dto.OtpMailDTO;
import com.thanhnt.cinemasystem.service.MailQueueProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(MailQueueProducerImpl.class);
  @Override
  public void sendMailMessage(OtpMailDTO mailInfo) {
    LOGGER.info("Sending mail message to queue: ");
    rabbitTemplate.convertAndSend(exchange, messageRoutingKey, mailInfo);
  }
}
