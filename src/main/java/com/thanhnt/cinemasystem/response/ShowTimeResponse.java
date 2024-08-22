package com.thanhnt.cinemasystem.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ShowTimeResponse {
  private Long id;
  private Long time;
}
