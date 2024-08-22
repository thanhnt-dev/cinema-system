package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Payment;
import java.util.Optional;

public interface PaymentService {
  void createPayment(Payment payment);

  Optional<Payment> findByOrderCode(String orderCode);
}
