package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.dto.LocationCsvDTO;
import com.thanhnt.cinemasystem.entity.District;
import com.thanhnt.cinemasystem.entity.Province;
import com.thanhnt.cinemasystem.entity.Ward;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.ImportException;
import com.thanhnt.cinemasystem.facade.LocationFacade;
import com.thanhnt.cinemasystem.helper.CSVHelper;
import com.thanhnt.cinemasystem.request.ImportRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import com.thanhnt.cinemasystem.response.WardResponse;
import com.thanhnt.cinemasystem.service.DistrictService;
import com.thanhnt.cinemasystem.service.ProvinceService;
import com.thanhnt.cinemasystem.service.WardService;
import java.io.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class LocationFacadeImpl implements LocationFacade {
  private final ProvinceService provinceService;
  private final DistrictService districtService;
  private final WardService wardService;

  @Override
  public List<ProvinceResponse> getAllProvince() {
    return provinceService.findAllProvince();
  }

  @Override
  public List<DistrictResponse> getDistrictByProvince(Long id) {
    return districtService.findByProvince(id);
  }

  @Override
  public List<WardResponse> getWardByDistrict(Long id) {
    return wardService.findWardByDistrictId(id);
  }

  @Override
  @Transactional
  public BaseResponse<Void> importData(ImportRequest request) {
    List<LocationCsvDTO> locationCsvDTOS = getLocationFromCsv(request.getFile());
    handleImportLocation(locationCsvDTOS);
    return BaseResponse.ok();
  }

  private List<LocationCsvDTO> getLocationFromCsv(MultipartFile file) {
    CSVHelper<LocationCsvDTO> csvDTOCSVHelper =
        new CSVHelper<>(
            csvRecord -> {
              String provicneName = null;
              Long provinceId = null;
              String districtName = null;
              Long districtId = null;
              String wardName = null;
              Long wardId = null;
              if (!csvRecord.get(0).isBlank()) provicneName = csvRecord.get(0);

              if (!csvRecord.get(1).isBlank()) provinceId = Long.valueOf(csvRecord.get(1));

              if (!csvRecord.get(2).isBlank()) districtName = csvRecord.get(2);

              if (!csvRecord.get(3).isBlank()) districtId = Long.valueOf(csvRecord.get(3));

              if (!csvRecord.get(4).isBlank()) wardName = csvRecord.get(4);

              if (!csvRecord.get(5).isBlank()) wardId = Long.valueOf(csvRecord.get(5));

              return LocationCsvDTO.builder()
                  .provinceName(provicneName)
                  .provinceId(provinceId)
                  .districtName(districtName)
                  .districtId(districtId)
                  .wardName(wardName)
                  .wardId(wardId)
                  .build();
            });

    try {
      InputStream inputStream = file.getInputStream();
      return csvDTOCSVHelper.parceCsv(file.getInputStream());
    } catch (IOException e) {
      throw new ImportException(ErrorCode.IMPORT_LOCATION_ERROR);
    }
  }

  @SneakyThrows
  private void handleImportLocation(List<LocationCsvDTO> locationCsvDTOS) {
    for (var locationcsv : locationCsvDTOS) {
      String provinceName = locationcsv.getProvinceName();
      Long provinceCode = locationcsv.getProvinceId();
      String districtName = locationcsv.getDistrictName();
      Long districtCode = locationcsv.getDistrictId();
      String wardName = locationcsv.getWardName();
      Long wardCode = locationcsv.getWardId();

      if (provinceName == null
          || provinceCode == null
          || districtName == null
          || districtCode == null
          || wardName == null
          || wardCode == null) {
        continue;
      }

      Province province = buildProvince(provinceCode, provinceName);
      District district = buildDistrict(districtCode, districtName, province);
      buildWard(wardCode, wardName, district);
    }
  }

  private Province buildProvince(Long provinceCode, String provinceName) {
    Province province = provinceService.findProvinceByProvinceCode(provinceCode);
    if (province == null) {
      province = Province.builder().provinceName(provinceName).provinceCode(provinceCode).build();
      provinceService.save(province);
    }
    return province;
  }

  private District buildDistrict(Long districtCode, String districtName, Province province) {
    District district = districtService.findByDistrictCode(districtCode);
    if (district == null) {
      district =
          District.builder()
              .districtCode(districtCode)
              .districtName(districtName)
              .province(province)
              .build();

      districtService.save(district);
    }
    return district;
  }

  public void buildWard(Long wardCode, String wardName, District district) {
    Ward ward = wardService.findWarByWardCode(wardCode);
    if (ward == null) {
      wardService.save(
          Ward.builder().wardCode(wardCode).wardName(wardName).district(district).build());
    }
  }
}
