package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Payment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
  Optional<Payment> findPaymentByOrder(String orderCode);
}
