package com.reservation.movie.payment.model;

import com.reservation.movie.payment.dto.PaymentDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@Builder
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String orderId;

  @Column(nullable = false)
  private int amount;

  @Column(nullable = false)
  private String createdAt;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private String paymentKey;

  public static Payment from(PaymentDto dto){
    return Payment.builder()
        .status(dto.getState())
        .paymentKey(dto.getPaymentKey())
        .orderId(dto.getOrderId())
        .createdAt(dto.getCreatedAt())
        .amount(dto.getAmount())
        .email(dto.getEmail())
        .build();
  }
}