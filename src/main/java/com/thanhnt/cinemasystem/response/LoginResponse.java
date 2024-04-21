package com.thanhnt.cinemasystem.response;

import com.thanhnt.cinemasystem.enums.RoleUser;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String accessToken;
  private String refreshToken;
  private List<RoleUser> roleUsers;
}
