package com.thanhnt.cinemasystem.response;

import com.thanhnt.cinemasystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProfileResponse {
  private String email;
  private String name;
  private Gender gender;
  private String phone;
  private Long dateOfBirth;
  private ProvinceResponse province;
  private DistrictResponse district;
  private WardResponse ward;
}
