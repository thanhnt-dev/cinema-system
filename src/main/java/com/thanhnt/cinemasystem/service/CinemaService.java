package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Cinema;
import java.util.List;

public interface CinemaService {
  List<Cinema> findByProvince(Long provinceId);
}
