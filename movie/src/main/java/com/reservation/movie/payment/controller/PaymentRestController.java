package com.reservation.movie.payment.controller;

import com.reservation.movie.common.ApiResponse;
import com.reservation.movie.payment.dto.PaymentDto;
import com.reservation.movie.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentRestController {

  private final PaymentService paymentService;

  @PostMapping("/confirm")
  public ResponseEntity<ApiResponse<PaymentDto>> payment(@RequestBody PaymentDto paymentDto){
    paymentService.confirmPayment(paymentDto);
    return ResponseEntity.ok(ApiResponse.success(paymentDto));
  }


}