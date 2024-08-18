package com.thanhnt.cinemasystem.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailResponse {
  private Long id;
  private String name;
  private String image;
  private String director;
  private String cast;
  private Long releaseDate;
  private Long endDate;
  private int duration;
  private String origin;
  private int ageRated;
  private String description;
  private String trailer;
  private String genre;
}
