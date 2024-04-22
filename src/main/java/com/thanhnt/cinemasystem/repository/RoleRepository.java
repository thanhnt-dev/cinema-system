package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Role;
import com.thanhnt.cinemasystem.enums.RoleUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleUser roleName);
}
