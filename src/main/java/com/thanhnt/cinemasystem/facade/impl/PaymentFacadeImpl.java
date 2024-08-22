package com.thanhnt.cinemasystem.facade.impl;

import com.thanhnt.cinemasystem.entity.TicketOrder;
import com.thanhnt.cinemasystem.entity.User;
import com.thanhnt.cinemasystem.enums.ErrorCode;
import com.thanhnt.cinemasystem.exception.LoginException;
import com.thanhnt.cinemasystem.facade.PaymentFacade;
import com.thanhnt.cinemasystem.request.PaymentRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import com.thanhnt.cinemasystem.security.SecurityUserDetails;
import com.thanhnt.cinemasystem.service.OrderService;
import com.thanhnt.cinemasystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentFacadeImpl implements PaymentFacade {
    private final UserService userService;
    private final OrderService orderService;
    @Override
    public BaseResponse<String> payment(PaymentRequest request) {
        var principal =
                (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user =
                this.userService
                        .findById(principal.getId())
                        .orElseThrow(() -> new LoginException(ErrorCode.USER_NOT_FOUND));

        TicketOrder ticketOrder = orderService.findById(request.getOrderId());

        return null;
    }
}
