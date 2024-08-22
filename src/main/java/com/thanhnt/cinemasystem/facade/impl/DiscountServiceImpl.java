package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Discount;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.DiscountException;
import com.thanhnt.cinemasystem.facade.DiscountService;
import com.thanhnt.cinemasystem.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {
  private final DiscountRepository discountRepository;

  @Override
  public Discount findDiscountByCode(String code) {
    return discountRepository
        .findByCode(code)
        .orElseThrow(() -> new DiscountException(ErrorCode.DISCOUNT_NOT_FOUND_OR_EXPIRED));
  }

  @Override
  @Transactional
  public void saveDiscount(Discount discount) {
    discountRepository.save(discount);
  }
}
