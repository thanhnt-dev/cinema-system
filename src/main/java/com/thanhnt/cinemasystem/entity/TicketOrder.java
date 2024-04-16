package com.thanhnt.cinemasystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ticket_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TicketOrder extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id", nullable = false)
    private MovieScreen movieScreen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", referencedColumnName = "id", nullable = false)
    private Seat seatOrder;

    @Column(name = "is_payment", nullable = false)
    @Builder.Default
    private boolean isPayment = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_code", referencedColumnName = "id")
    private Discount discount;

    @Column(name = "discount_amount")
    private Long discountAmount;
}
