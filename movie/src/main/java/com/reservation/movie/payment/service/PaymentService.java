package com.reservation.movie.payment.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentService {

public String confirmPayment(String paymentKey, String orderId, int amount){

  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  String email = auth.getName();

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
  String formattedTime = LocalDateTime.now().format(formatter);

  
  return "";
}
}
