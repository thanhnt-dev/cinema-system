package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "payment_historys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Payment extends BaseEntity implements Serializable {
    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private TicketOrder order;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "amount")
    private Float totalPrice;
}
