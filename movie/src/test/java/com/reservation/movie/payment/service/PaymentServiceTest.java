package com.reservation.movie.payment.service;

import com.reservation.movie.payment.dto.PaymentDto;
import com.reservation.movie.payment.model.Payment;
import com.reservation.movie.payment.repository.PaymentRepository;
import com.reservation.movie.payment.service.PaymentService;
import com.reservation.movie.user.service.AuthService;
import com.reservation.movie.user.userDto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
  @InjectMocks
  private PaymentService paymentService;

  @Mock
  private PaymentRepository paymentRepository;

  @Mock
  private Authentication authentication;

  @Mock
  private SecurityContext securityContext;

  @BeforeEach
  void setUp(){
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getName()).thenReturn("test@naver.com");

    ReflectionTestUtils.setField(
        paymentService,
        "secretKey",
        "test-secret-key"
    );

    RestTemplate mockRestTemplate = mock(RestTemplate.class);
    when(mockRestTemplate.postForEntity(
        anyString(),
        any(HttpEntity.class),
        eq(String.class)
    )).thenReturn(ResponseEntity.ok("ok"));

      ReflectionTestUtils.setField(
          paymentService,
          "restTemplate",
          mockRestTemplate
      );
  }

  @Test
  void paymentSuccess(){

    PaymentDto paymentDto = PaymentDto.builder()
        .orderId("orderId")
        .amount(123)
        .paymentKey("paymentKey")
        .build();

    String result = paymentService.confirmPayment(paymentDto);
    verify(paymentRepository, times(1)).save(any(Payment.class));

    assertThat(result).isEqualTo("ok");
  }
}