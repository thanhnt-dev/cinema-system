package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.entity.Discount;

public interface DiscountService {
  Discount findDiscountByCode(String code);

  void saveDiscount(Discount discount);
}
