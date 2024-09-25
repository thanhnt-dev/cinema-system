package com.thanhnt.cinemasystem.facade;

import com.thanhnt.cinemasystem.request.PaymentRequest;
import com.thanhnt.cinemasystem.request.VnPayCallbackParam;
import com.thanhnt.cinemasystem.response.BaseResponse;

public interface PaymentFacade {
  BaseResponse<String> payment(PaymentRequest paymentRequest);

  BaseResponse<Void> handleVnPaySuccess(VnPayCallbackParam callback);
}
