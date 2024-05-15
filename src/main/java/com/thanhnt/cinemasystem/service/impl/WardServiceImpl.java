package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Ward;
import com.thanhnt.cinemasystem.repository.WardRepository;
import com.thanhnt.cinemasystem.response.WardResponse;
import com.thanhnt.cinemasystem.service.WardService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {
  private final WardRepository wardRepository;

  @Override
  public Optional<Ward> findWardById(Long id) {
    return wardRepository.findById(id);
  }

  @Override
  public Ward findWarByWardCode(Long code) {
    if (code == null) {
      return null;
    }
    return wardRepository.findByWardCode(code);
  }

  @Override
  public List<WardResponse> findWardByDistrictId(Long id) {
    List<Ward> wards = wardRepository.findByDistrictId(id);
    return wards.stream()
        .map(ward -> WardResponse.builder().id(ward.getId()).wardName(ward.getWardName()).build())
        .collect(Collectors.toList());
  }

  @Override
  public void save(Ward ward) {
    wardRepository.save(ward);
  }
}
