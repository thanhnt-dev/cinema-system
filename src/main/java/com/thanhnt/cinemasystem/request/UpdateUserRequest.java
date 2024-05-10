package com.thanhnt.cinemasystem.request;

import com.thanhnt.cinemasystem.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateUserRequest {
  @Schema(description = "Name of the user", example = "Nguyen Van A")
  private String name;

  @Schema(description = "Gender", example = "MALE")
  private Gender gender;

  @Schema(description = "Phone number", example = "0123456789")
  private String phone;

  @Schema(description = "Date of birth", example = "2024-05-09T00:00:00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Long dateOfBirth;

  @Schema(description = "Province", example = "TH HCM")
  private Long provinceID;

  @Schema(description = "District", example = "THU DUC")
  private Long districtID;

  @Schema(description = "Ward", example = "Linh Trung")
  private Long wardID;
}
