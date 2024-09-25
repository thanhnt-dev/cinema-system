package com.thanhnt.cinemasystem.service;

import com.thanhnt.cinemasystem.entity.TicketOrder;
import java.util.Optional;

public interface OrderService {
  TicketOrder findOrderBySeatIdAndShowTimeId(Long seatId, Long showtimeId);

  TicketOrder findById(Long id);

  Optional<TicketOrder> findByOrderCode(String orderCode);

  void saveOrder(TicketOrder order);
}
