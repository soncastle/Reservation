package payment.service;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
  @InjectMocks
  private PaymentService paymentService;

  @Mock
  private PaymentRepository paymentRepository;

  @Mock
  private AuthService authService;

  @Mock
  private Authentication authentication;

  @Mock
  private SecurityContext securityContext;

  @BeforeEach
  void setUp(){
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getName()).thenReturn("test@naver.com");
  }

  @Test
  void paymentSuccess(){

    UserDto userDto = new UserDto();
    when(authService.checkUserSession()).thenReturn(userDto);

    PaymentDto paymentDto = PaymentDto.builder()
        .orderId("orderId")
        .createdAt("now")
        .amount(123)
        .state("confirm")
        .paymentKey("paymentKey")
        .email("test@naver.com")
        .build();

    String result = paymentService.confirmPayment(paymentDto);
    verify(paymentRepository, times(1)).save(any(Payment.class));

    assertThat(result).isEqualTo("저장완");
  }
}