package com.thanhnt.cinemasystem.entity;

import com.thanhnt.cinemasystem.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "is_status", nullable = false)
    @Builder.Default
    private boolean isStatus = false;

    @Column(name = "price", nullable = false)
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room roomSeat;

    @OneToMany(mappedBy = "seatOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<TicketOrder> seats = new ArrayList<>();
}
