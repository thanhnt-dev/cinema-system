package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.Payment;
import com.thanhnt.cinemasystem.entity.TicketOrder;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.enums.PaymentStatus;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.exception.OrderException;
import com.thanhnt.cinemasystem.facade.PaymentFacade;
import com.thanhnt.cinemasystem.request.PaymentRequest;
import com.thanhnt.cinemasystem.request.VnPayCallbackParam;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.OrderService;
import com.thanhnt.cinemasystem.service.PaymentService;
import com.thanhnt.cinemasystem.service.UserService;
import com.thanhnt.cinemasystem.service.VnPayService;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentFacadeImpl implements PaymentFacade {
  private final UserService userService;
  private final OrderService orderService;
  private final VnPayService vnPayService;
  private final PaymentService paymentService;

  @Override
  public BaseResponse<String> payment(PaymentRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user =
        this.userService
            .findById(principal.getId())
            .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

    TicketOrder ticketOrder = orderService.findById(request.getOrderId());

    Long amount = (long) (request.getAmount() * 100L);
    var vnpParams = vnPayService.buildVnPayParams(amount, request.getOrderCode());
    List<String> fieldNames = new ArrayList<>(vnpParams.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    Iterator itr = fieldNames.iterator();

    while (itr.hasNext()) {
      String fieldName = (String) itr.next();
      String fieldValue = vnpParams.get(fieldName);
      if ((fieldValue != null) && (!fieldValue.isEmpty())) {

        hashData.append(fieldName);
        hashData.append('=');
        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
        query.append('=');
        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
        if (itr.hasNext()) {
          query.append('&');
          hashData.append('&');
        }
      }
    }
    String queryUrl = query.toString();
    String vnpSecureHash = vnPayService.hmacSHA512(hashData.toString());
    queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
    String paymentUrl = vnPayService.getPaymentUrl(queryUrl);

    paymentService.createPayment(
        Payment.builder()
            .order(ticketOrder.getOrderCode())
            .status(PaymentStatus.PENDING)
            .totalPrice(request.getAmount())
            .build());
    return BaseResponse.build(paymentUrl, true);
  }

  @Override
  public BaseResponse<Void> handleVnPaySuccess(VnPayCallbackParam callback) {
    Payment payment = paymentService.findByOrderCode(callback.getVnp_TxnRef()).orElse(null);
    if (payment == null) return BaseResponse.fail();

    TicketOrder ticketOrder =
        orderService
            .findByOrderCode(callback.getVnp_TxnRef())
            .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));
    ticketOrder.updatePayment();
    payment.updateStatus();
    orderService.saveOrder(ticketOrder);
    paymentService.createPayment(payment);
    return BaseResponse.ok();
  }
}
