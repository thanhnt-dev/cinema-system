package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.Seat;

public interface SeatService {
  Seat findSeatById(Long id);
}
