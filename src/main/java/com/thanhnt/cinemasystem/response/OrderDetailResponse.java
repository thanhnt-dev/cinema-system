package com.thanhnt.cinemasystem.response;

import com.thanhnt.cinemasystem.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderDetailResponse {
  private Long cinemaId;
  private String cinemaName;
  private String cinemaAddress;
  private String orderCode;
  private Long movieId;
  private String movieName;
  private String roomCode;
  private SeatType seatType;
  private String seatCode;
  private Long showTime;
  private Float price;
}
