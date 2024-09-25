package com.thanhnt.cinemasystem.service;

import java.util.Map;

public interface VnPayService {
  String hmacSHA512(String data);

  Map<String, String> buildVnPayParams(Long amount, String orderCode);

  String getPaymentUrl(String queryUrl);
}
