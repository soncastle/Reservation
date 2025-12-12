package com.reservation.movie.payment.controller;

import com.reservation.movie.common.ApiResponse;
import com.reservation.movie.payment.dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

  @PostMapping("/confirm")
  public ResponseEntity<ApiResponse<PaymentDto>> payment(){
    PaymentDto paymentDto = new PaymentDto();
    paymentDto.setPaymentKey("gg");
    paymentDto.setOrderId(2123);
    paymentDto.setAmount(123);
    return ResponseEntity.ok(ApiResponse.success(paymentDto));
  }
}