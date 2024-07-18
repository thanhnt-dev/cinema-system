package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.CinemaResponse;
import java.util.List;

public interface CinemaFacade {
  BaseResponse<List<CinemaResponse>> getCinemaByProvince(Long id);
}
