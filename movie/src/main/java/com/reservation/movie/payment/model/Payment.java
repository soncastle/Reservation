package com.reservation.movie.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private long orderId;

  @Column(nullable = false)
  private int amount;

  @Column(nullable = false)
  private String createdAt;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private String paymentKey;
}