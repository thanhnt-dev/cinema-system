package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
  List<District> findDistrictByProvinceId(Long provinceId);
}
