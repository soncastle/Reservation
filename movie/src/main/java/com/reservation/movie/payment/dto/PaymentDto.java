package com.reservation.movie.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
  private String orderId;
  private int amount;
  private String paymentKey;
  private String email;
  private String createdAt;
  private String state;
}

