package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {}
