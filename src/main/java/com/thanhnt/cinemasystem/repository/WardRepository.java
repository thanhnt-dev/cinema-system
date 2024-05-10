package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {}
