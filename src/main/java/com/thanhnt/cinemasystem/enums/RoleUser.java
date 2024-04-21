package com.thanhnt.cinemasystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleUser {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER"),
  USERVIP("ROLE_USERVIP"),
  STAFF("ROLE_STAFF");

  private final String roleName;

  public static RoleUser getRoleName(String roleName) {
    for (RoleUser role : RoleUser.values()) {
      if (role.getRoleName().equals(roleName)) {
        return role;
      }
    }
    return null;
  }
}
