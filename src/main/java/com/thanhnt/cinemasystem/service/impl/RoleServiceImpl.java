package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.enums.RoleUser;
import com.thanhnt.cinemasystem.repository.RoleRepository;
import com.thanhnt.cinemasystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public Role findRole(RoleUser name) {
    return roleRepository.findByName(name);
  }
}
