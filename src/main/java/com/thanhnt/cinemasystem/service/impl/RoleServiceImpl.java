package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.RoleException;
import com.thanhnt.cinemasystem.reponsitory.RoleRepository;
import com.thanhnt.cinemasystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public Role findRole(String name) {
    return roleRepository
        .findByName(name)
        .orElseThrow(() -> new RoleException(ErrorCode.ROLE_NOT_FOUND));
  }
}
