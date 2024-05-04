package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.dto.OTPMailDTO;
import com.thanhnt.cinemasystem.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender javaMailSender;

  @Override
  public void sendMail(OTPMailDTO otpMailDTO) {
    String subject =
        otpMailDTO.getType().isRegister() ? "Verify Your Email: " : "OTP For Reset Password: ";
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(otpMailDTO.getReceiverMail());
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(
        String.format("Hello! This is your OTP code: %s", otpMailDTO.getOtpCode()));
    javaMailSender.send(simpleMailMessage);
  }
}
