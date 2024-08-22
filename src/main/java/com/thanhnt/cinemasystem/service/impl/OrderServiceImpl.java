package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.entity.TicketOrder;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.OrderException;
import com.thanhnt.cinemasystem.repository.OrderRepository;
import com.thanhnt.cinemasystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public TicketOrder findOrderBySeatIdAndShowTimeId(Long seatId, Long showtimeId) {
    return orderRepository.findOrderBySeatIdAndShowTimeId(seatId, showtimeId);
  }

  @Override
  public TicketOrder findById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));
  }

  @Override
  @Transactional
  public void saveOrder(TicketOrder order) {
    orderRepository.save(order);
  }
}
