package com.reservation.movie.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
  private String email;
  private long orderId;
  private int amount;
  private String createdAt;
  private String status;
  private String paymentKey;
}

