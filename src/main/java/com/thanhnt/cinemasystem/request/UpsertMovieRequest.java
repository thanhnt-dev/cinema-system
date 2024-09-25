package com.thanhnt.cinemasystem.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertMovieRequest {
  private Long id;

  @NotEmpty(message = "Movie name is required")
  private String name;

  private String director;
  private String cast;

  @NotEmpty(message = "Release Date is required")
  private Long releaseDate;

  @NotEmpty(message = "End Date is required")
  private Long endDate;

  private Integer duration;
  private String description;
  private String language;
  private String trailer;
  private Integer age;

  @NotEmpty(message = "Genre id is required")
  private Long genre;
}
