package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Cinema;
import com.thanhnt.cinemasystem.facade.CinemaFacade;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.CinemaResponse;
import com.thanhnt.cinemasystem.service.CinemaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaFacadeImpl implements CinemaFacade {
  private final CinemaService cinemaService;

  @Override
  public BaseResponse<List<CinemaResponse>> getCinemaByProvince(Long provinceId) {
    List<Cinema> cinemas = cinemaService.findByProvince(provinceId);
    List<CinemaResponse> list =
        cinemas.stream()
            .map(
                cinema ->
                    CinemaResponse.builder()
                        .id(cinema.getId())
                        .provinceId(cinema.getProvince().getId())
                        .name(cinema.getCinemaName())
                        .build())
            .toList();

    return BaseResponse.build(list, true);
  }
}
