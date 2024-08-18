package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.TicketOrder;

public interface OrderService {
  TicketOrder findOrderBySeatIdAndShowTimeId(Long seatId, Long showtimeId);

  TicketOrder findById(Long id);

  void saveOrder(TicketOrder order);
}
