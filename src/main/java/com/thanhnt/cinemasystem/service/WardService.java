package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Ward;
import com.thanhnt.cinemasystem.response.WardResponse;
import java.util.List;
import java.util.Optional;

public interface WardService {
  Optional<Ward> findWardById(Long id);

  List<WardResponse> findWardByDistrictId(Long id);

  void save(Ward ward);
}
