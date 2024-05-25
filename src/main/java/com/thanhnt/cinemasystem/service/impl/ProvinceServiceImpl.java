package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Province;
import com.thanhnt.cinemasystem.repository.ProvinceRepository;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import com.thanhnt.cinemasystem.service.ProvinceService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
  private final ProvinceRepository provinceRepository;

  @Override
  public Optional<Province> findProvinceById(Long id) {
    return provinceRepository.findById(id);
  }

  @Override
  public List<ProvinceResponse> findAllProvince() {
    List<Province> provinces = provinceRepository.findAll();
    return provinces.stream()
        .map(
            province ->
                ProvinceResponse.builder()
                    .id(province.getId())
                    .provinceName(province.getProvinceName())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public void save(Province province) {
    provinceRepository.save(province);
  }
}
