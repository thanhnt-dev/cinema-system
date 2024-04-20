package com.thanhnt.cinemasystem.responsitory;

import com.thanhnt.cinemasystem.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String roleName);
}
