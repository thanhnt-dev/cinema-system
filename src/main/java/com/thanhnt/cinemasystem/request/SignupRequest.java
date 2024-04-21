package com.thanhnt.cinemasystem.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SignupRequest {
  private String email;
  private String password;
  private String name;
  private String phone;
  private Long dateOfBirth;
}