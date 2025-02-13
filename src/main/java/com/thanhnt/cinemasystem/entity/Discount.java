package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Discount extends BaseEntity implements Serializable {
  @Column(name = "discount_code", length = 50, nullable = false)
  private String discountCode;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "expiration_date")
  private Long expirationDate;

  @Column(name = "discount_percentage", nullable = false)
  private float discountPercentage;

  @Column(name = "max_discount_amount", nullable = false)
  private Long maxDiscountAmount;

  public void decreaseQuantity() {
    quantity = quantity - 1;
  }
}
