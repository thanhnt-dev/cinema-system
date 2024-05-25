package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Ward;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
  List<Ward> findByDistrictId(Long districtId);
}
