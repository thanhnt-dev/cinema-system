package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.UnauthorizedException;
import com.thanhnt.cinemasystem.facade.LocationFacade;
import com.thanhnt.cinemasystem.request.ImportRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import com.thanhnt.cinemasystem.response.WardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

  private final LocationFacade locationFacade;

  @GetMapping("/provinces")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  @PreAuthorize("isAuthenticated()")
  @SecurityRequirement(name = "Bearer Authentication")
  public List<ProvinceResponse> getProvince() {
    return this.locationFacade.getAllProvince();
  }

  @GetMapping("/districts/{provinceId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  @PreAuthorize("isAuthenticated()")
  @SecurityRequirement(name = "Bearer Authentication")
  public List<DistrictResponse> getDistrictsByProvince(@PathVariable Long provinceId) {
    return this.locationFacade.getDistrictByProvince(provinceId);
  }

  @GetMapping("/wards/{districtId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"LOCATION APIs"},
      summary = "Location")
  @PreAuthorize("isAuthenticated()")
  @SecurityRequirement(name = "Bearer Authentication")
  public List<WardResponse> getWardsByDistrict(@PathVariable Long districtId) {
    return this.locationFacade.getWardByDistrict(districtId);
  }

  @PostMapping(
      value = "/imports",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Import location by csv file",
      tags = {"LOCATION APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> importLocation(
      @Valid ImportRequest request, @RequestHeader("API_KEY") String apiKey) throws IOException {
    if (!apiKey.equals("123")) throw new UnauthorizedException(ErrorCode.API_KEY_INVALID);
    return locationFacade.importData(request);
  }
}
