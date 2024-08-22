package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TicketOrder extends BaseEntity implements Serializable {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_showtime", nullable = false)
  private MovieTime movieTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id", referencedColumnName = "id", nullable = false)
  private Seat seat;

  @Column(name = "is_payment", nullable = false)
  @Builder.Default
  private boolean isPayment = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_code", referencedColumnName = "id")
  private Discount discount;

  @Column(name = "discount_amount")
  private Float discountAmount;

  @Column(name = "price")
  private Float price;
}
