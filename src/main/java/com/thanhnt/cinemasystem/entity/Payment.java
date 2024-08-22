package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.PaymentStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_historys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Payment extends BaseEntity implements Serializable {
  @Column(name = "order_code", nullable = false)
  private String order;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  @Column(name = "amount")
  private Float totalPrice;

  public void updateStatus() {
    this.status = PaymentStatus.SUCCESS;
  }
}
