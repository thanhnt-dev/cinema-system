package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.OrderFacade;
import com.thanhnt.cinemasystem.request.OrderRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.response.OrderDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderFacade orderFacade;

  @PostMapping()
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"ORDERS APIs"},
      summary = "Create a order movie")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> createOrder(@RequestBody OrderRequest request) {
    return this.orderFacade.createOrder(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"ORDERS APIs"},
      summary = "Get order detail by order Id")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<OrderDetailResponse> getOrderDetail(@PathVariable("id") Long id) {
    return this.orderFacade.getOrderDetail(id);
  }
}
