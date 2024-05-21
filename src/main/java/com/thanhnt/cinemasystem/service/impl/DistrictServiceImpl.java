package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.District;
import com.thanhnt.cinemasystem.repository.DistrictRepository;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import com.thanhnt.cinemasystem.service.DistrictService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
  private final DistrictRepository districtRepository;

  @Override
  public Optional<District> findDistrictById(Long id) {
    return districtRepository.findById(id);
  }

  @Override
  public List<DistrictResponse> findByProvince(Long id) {
    List<District> districts = districtRepository.findDistrictByProvinceId(id);
    return districts.stream()
        .map(
            district ->
                DistrictResponse.builder()
                    .id(district.getId())
                    .districtName(district.getDistrictName())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public void save(District district) {
    districtRepository.save(district);
  }
}
