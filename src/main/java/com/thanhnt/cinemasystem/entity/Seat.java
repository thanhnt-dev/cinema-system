package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.SeatType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Seat extends BaseEntity implements Serializable {
  @Column(name = "seat_code", nullable = false, length = 3)
  private String seatCode;

  @Column(name = "seat_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private SeatType seatType;

  @Column(name = "is_blocked", nullable = false)
  @Builder.Default
  private boolean isBooked = false;

  @Column(name = "price", nullable = false)
  private long price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

  @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<TicketOrder> ticketOrders = new ArrayList<>();
}
