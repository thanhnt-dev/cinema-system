package com.thanhnt.cinemasystem.request;

import com.thanhnt.cinemasystem.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull(message = "Email is require")
  @NotBlank(message = "Email can not be blank")
  private String name;

  @Schema(description = "Gender", example = "MALE")
  @NotNull(message = "Gender is require")
  private Gender gender;

  @Schema(description = "Phone number", example = "0123456789")
  @NotNull(message = "Phone is require")
  @NotBlank(message = "Phone can not be blank")
  private String phone;

  @Schema(description = "Date of birth", example = "2024-05-09T00:00:00")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @NotNull(message = "Gender is require")
  private Long dateOfBirth;

  @Schema(description = "Province", example = "1")
  @NotNull(message = "Province is require")
  private Long provinceID;

  @Schema(description = "District", example = "2")
  @NotNull(message = "District is require")
  private Long districtID;

  @Schema(description = "Ward", example = "3")
  @NotNull(message = "Ward is require")
  private Long wardID;
}
