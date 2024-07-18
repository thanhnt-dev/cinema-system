package com.thanhnt.cinemasystem.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CinemaResponse {
  private Long id;
  private String name;
  private Long provinceId;
}
