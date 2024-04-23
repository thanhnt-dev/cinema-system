package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.enums.RoleUser;
import java.util.Optional;

public interface RoleService {
  Optional<Role> findRole(RoleUser name);
}
