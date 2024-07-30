package com.thanhnt.cinemasystem.response;

import com.thanhnt.cinemasystem.enums.Language;
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
  private Long premiere;
  private int duration;
  private Language language;
  private int ageRated;
  private String description;
  private String trailer;
  private String genre;
}
