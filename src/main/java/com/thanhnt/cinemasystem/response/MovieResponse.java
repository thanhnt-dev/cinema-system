package com.thanhnt.cinemasystem.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MovieResponse {
  private Long id;
  private String name;
  private String image;
  private int duration;
  private Long premiere;
  private String genre;
}
