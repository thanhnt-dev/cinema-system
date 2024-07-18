package com.thanhnt.cinemasystem.repository;

import com.thanhnt.cinemasystem.entity.Cinema;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

  List<Cinema> findByProvinceId(Long provinceId);
}
