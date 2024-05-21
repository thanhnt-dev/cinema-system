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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    CSVHelper<LocationCsvDTO> csvDTOHelper =
        new CSVHelper<>(
            csvRecord -> {
              String provicneName = null;
              String districtName = null;
              String wardName = null;
              if (!csvRecord.get(0).isBlank()) provicneName = csvRecord.get(0);

              if (!csvRecord.get(1).isBlank()) districtName = csvRecord.get(1);

              if (!csvRecord.get(2).isBlank()) wardName = csvRecord.get(2);

              return LocationCsvDTO.builder()
                  .provinceName(provicneName)
                  .districtName(districtName)
                  .wardName(wardName)
                  .build();
            });

    try {
      return csvDTOHelper.parceCsv(file.getInputStream());
    } catch (IOException e) {
      throw new ImportException(ErrorCode.IMPORT_LOCATION_ERROR);
    }
  }

  @SneakyThrows
  private void handleImportLocation(List<LocationCsvDTO> locationCsvDTOS) {
    Map<Province, Map<District, List<Ward>>> mapProvince = new HashMap<>();
    for (var locationcsv : locationCsvDTOS) {
      String provinceName = locationcsv.getProvinceName();
      String districtName = locationcsv.getDistrictName();
      String wardName = locationcsv.getWardName();

      if (provinceName == null || districtName == null || wardName == null) {
        continue;
      }
      Province province = findProvinceByNameInMap(mapProvince, provinceName);

      if (province == null) {
        province = Province.builder().provinceName(provinceName).build();
        mapProvince.put(province, new HashMap<>());
      }
      ;

      Map<District, List<Ward>> mapDistrict = mapProvince.get(province);
      District district = District.builder().districtName(districtName).province(province).build();
      mapDistrict.putIfAbsent(district, new ArrayList<>());

      List<Ward> wardList = mapDistrict.get(district);
      wardList.add(Ward.builder().wardName(wardName).district(district).build());
    }

    for (Map.Entry<Province, Map<District, List<Ward>>> provinceMap : mapProvince.entrySet()) {
      Province province = provinceMap.getKey();
      Map<District, List<Ward>> districtListMap = provinceMap.getValue();
      for (Map.Entry<District, List<Ward>> districtMap : districtListMap.entrySet()) {
        District district = districtMap.getKey();
        List<Ward> wards = districtMap.getValue();

        provinceService.save(province);
        districtService.save(district);
        for (Ward ward : wards) {
          wardService.save(Ward.builder().wardName(ward.getWardName()).district(district).build());
        }
      }
    }
  }

  private Province findProvinceByNameInMap(
      Map<Province, Map<District, List<Ward>>> map, String provinceName) {
    for (Province province : map.keySet()) {
      if (province.getProvinceName().equals(provinceName)) {
        return province;
      }
    }
    return null;
  }
}
