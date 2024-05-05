package com.thanhnt.cinemasystem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.name}")
  private String mailQueue;

  @Value("${rabbitmq.exchangeName}")
  private String exchange;

  @Value("${rabbitmq.mailRoutingKey}")
  private String mailRoutingKey;

  @Bean
  public Queue queue() {
    return new Queue(mailQueue);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(exchange()).with(mailRoutingKey);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }
}
