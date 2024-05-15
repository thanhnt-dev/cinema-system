package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.ImportRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.DistrictResponse;
import com.thanhnt.cinemasystem.response.ProvinceResponse;
import com.thanhnt.cinemasystem.response.WardResponse;
import java.io.IOException;
import java.util.List;

public interface LocationFacade {
  List<ProvinceResponse> getAllProvince();

  List<DistrictResponse> getDistrictByProvince(Long id);

  List<WardResponse> getWardByDistrict(Long id);

  BaseResponse<Void> importData(ImportRequest request) throws IOException;
}
