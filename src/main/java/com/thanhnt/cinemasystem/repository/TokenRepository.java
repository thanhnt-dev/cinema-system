package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
  void deleteByUserId(Long id);
}
