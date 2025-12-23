package com.reservation.movie.payment.service;

import com.reservation.movie.payment.dto.PaymentDto;
import com.reservation.movie.payment.model.Payment;
import com.reservation.movie.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

  @Value("${toss.secret-key}")
  private String secretKey;

  private final RestTemplate restTemplate = new RestTemplate();
  private final PaymentRepository paymentRepository;

public String confirmPayment(PaymentDto paymentDto){

  String url = "https://api.tosspayments.com/v1/payments/confirm";

  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);

  headers.setBasicAuth(secretKey, "");

  Map<String, Object> body = new HashMap<>();
  body.put("paymentKey", paymentDto.getPaymentKey());
  body.put("orderId", paymentDto.getOrderId());
  body.put("amount", paymentDto.getAmount());

  HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

  ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  String email = auth.getName();

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  String formattedTime = LocalDateTime.now().format(formatter);

  paymentDto.setState("confirm");
  paymentDto.setEmail(email);
  paymentDto.setCreatedAt(formattedTime);

  Payment payment = Payment.from(paymentDto);
  paymentRepository.save(payment);

  return response.getBody();

  }
}