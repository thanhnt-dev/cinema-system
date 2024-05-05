package com.thanhnt.cinemasystem.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginRequest {

  private String email;
  private String password;
}
