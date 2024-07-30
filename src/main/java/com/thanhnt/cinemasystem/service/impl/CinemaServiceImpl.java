package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Cinema;
import com.thanhnt.cinemasystem.repository.CinemaRepository;
import com.thanhnt.cinemasystem.service.CinemaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {
  private final CinemaRepository cinemaRepository;

  @Override
  public List<Cinema> findByProvince(Long provinceId) {
    return cinemaRepository.findByProvinceId(provinceId);
  }
}
