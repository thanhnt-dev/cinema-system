package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.CinemaFacade;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.CinemaResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cinemas")
@RequiredArgsConstructor
public class CinemaController {
  private final CinemaFacade cinemaFacade;

  @GetMapping("/{provinceId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"CINEMA APIs"},
      summary = "Get Cinema By ProvinceID")
  public BaseResponse<List<CinemaResponse>> getMovieShowing(@PathVariable("provinceId") Long id) {
    return this.cinemaFacade.getCinemaByProvince(id);
  }
}
