package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.LocationFacade;
import com.thanhnt.cinemasystem.request.ImportRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import com.thanhnt.cinemasystem.response.WardResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {

  private final LocationFacade locationFacade;

  @GetMapping("/province")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  public List<ProvinceResponse> getProvince() {
    return this.locationFacade.getAllProvince();
  }

  @GetMapping("/districts/{provinceId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  public List<DistrictResponse> getDistrictsByProvince(@PathVariable Long provinceId) {
    return this.locationFacade.getDistrictByProvince(provinceId);
  }

  @GetMapping("/wards/{districtId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  public List<WardResponse> getWardsByDistrict(@PathVariable Long districtId) {
    return this.locationFacade.getWardByDistrict(districtId);
  }

  @PostMapping(
      value = "/import",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Import product by csv file",
      tags = {"Import APIs"})
  public BaseResponse<Void> importLocation(@Valid ImportRequest request) throws IOException {
    return this.locationFacade.importData(request);
  }
}
