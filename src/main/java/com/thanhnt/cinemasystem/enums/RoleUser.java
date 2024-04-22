package com.thanhnt.cinemasystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleUser {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_USER("ROLE_USER"),
  ROLE_USER_VIP("ROLE_USER_VIP"),
  STAFF("ROLE_STAFF");

  private final String roleName;

  public static String getRoleName(String roleName) {
    for (RoleUser role : RoleUser.values()) {
      if (role.getRoleName().equals(roleName)) return role.getRoleName();
    }
    return null;
  }
}
