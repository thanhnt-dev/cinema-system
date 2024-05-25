package com.thanhnt.cinemasystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationCsvDTO {
  private String provinceName;
  private String districtName;
  private String wardName;
}
