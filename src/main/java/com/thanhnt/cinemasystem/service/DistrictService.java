package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.District;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import java.util.List;
import java.util.Optional;

public interface DistrictService {
  Optional<District> findDistrictById(Long id);

  District findByDistrictCode(Long id);

  List<DistrictResponse> findByProvince(Long id);

  void save(District district);
}
