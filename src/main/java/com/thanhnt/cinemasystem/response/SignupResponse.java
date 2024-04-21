package com.thanhnt.cinemasystem.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupResponse {
  private String email;
  private String phone;
}
