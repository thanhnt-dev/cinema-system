package com.thanhnt.cinemasystem.reponsitory;

import com.thanhnt.cinemasystem.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String roleName);
}
