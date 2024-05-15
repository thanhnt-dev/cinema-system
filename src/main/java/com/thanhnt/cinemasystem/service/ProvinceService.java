package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Province;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import java.util.List;
import java.util.Optional;

public interface ProvinceService {
  Optional<Province> findProvinceById(Long id);

  List<ProvinceResponse> findAllProvince();

  Province findProvinceByProvinceCode(Long code);

  void save(Province province);
}
