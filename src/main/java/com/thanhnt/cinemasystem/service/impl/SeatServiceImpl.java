package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.Seat;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.SeatException;
import com.thanhnt.cinemasystem.repository.SeatRepository;
import com.thanhnt.cinemasystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {
  private final SeatRepository seatRepository;

  @Override
  public Seat findSeatById(Long id) {
    return seatRepository
        .findById(id)
        .orElseThrow(() -> new SeatException(ErrorCode.SEAT_NOT_FOUND));
  }
}
