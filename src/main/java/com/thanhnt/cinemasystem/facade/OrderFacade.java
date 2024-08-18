package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.OrderRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.OrderDetailResponse;

public interface OrderFacade {
  BaseResponse<Void> createOrder(OrderRequest request);

  BaseResponse<OrderDetailResponse> getOrderDetail(Long orderId);
}
