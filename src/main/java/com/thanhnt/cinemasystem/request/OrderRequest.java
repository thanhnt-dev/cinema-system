package com.thanhnt.cinemasystem.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderRequest {
  private String orderCode;
  private Long showtimeId;
  private Long seatId;
  private String discountCode;
}
