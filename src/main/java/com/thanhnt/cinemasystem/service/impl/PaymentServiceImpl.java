package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Payment;
import com.thanhnt.cinemasystem.repository.PaymentRepository;
import com.thanhnt.cinemasystem.service.PaymentService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;

  @Override
  public void createPayment(Payment payment) {
    paymentRepository.save(payment);
  }

  @Override
  public Optional<Payment> findByOrderCode(String orderCode) {
    return paymentRepository.findPaymentByOrder(orderCode);
  }
}
