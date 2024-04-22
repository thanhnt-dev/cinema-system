package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.enums.RoleUser;

public interface RoleService {
  Role findRole(RoleUser name);
}
