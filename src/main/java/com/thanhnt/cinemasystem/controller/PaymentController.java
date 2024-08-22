package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.facade.PaymentFacade;
import com.thanhnt.cinemasystem.request.PaymentRequest;
import com.thanhnt.cinemasystem.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFacade paymentFacade;

    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Payment order",
            tags = {"Sell Order APIs"})
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<String> payment(
            @RequestBody PaymentRequest request) {
        return this.paymentFacade.payment(request);
    }
}
