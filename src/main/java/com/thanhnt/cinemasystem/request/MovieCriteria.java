package com.thanhnt.cinemasystem.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCriteria extends BaseCriteria {
  private Integer currentPage;
  private Integer pageSize;
  private String movieName;
  private boolean isActive = true;
}
