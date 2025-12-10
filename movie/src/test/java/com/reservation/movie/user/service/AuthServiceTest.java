package com.reservation.movie.user.service;

import com.reservation.movie.reservation.repository.SeatReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.userDto.LoginRequestDto;
import com.reservation.movie.user.userDto.SignupRequestDto;
import com.reservation.movie.user.userDto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  @InjectMocks
  private AuthService authService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse res;

  @Mock
  private SecurityContextRepository securityContextRepository;

  @BeforeEach
  void loginInfo(){
    User savedUser = new User();
    savedUser.setEmail("test@naver.com");
    savedUser.setPassword("encodedPassword");
    savedUser.setRole("user");

    when(userRepository.findByEmail("test@naver.com"))
        .thenReturn(Optional.of(savedUser));

    when(passwordEncoder.matches("Test123!!", "encodedPassword"))
        .thenReturn(true);

    doNothing().when(securityContextRepository)
        .saveContext(any(), any(), any());
  }

  @Test
  void loginSuccess(){

  LoginRequestDto loginRequestDto = new LoginRequestDto();
  loginRequestDto.setEmail("test@naver.com");
  loginRequestDto.setPassword("Test123!!");

  authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword(), req, res);

  verify(userRepository, times(1)).findByEmail(loginRequestDto.getEmail());

  verify(passwordEncoder, times(1)).matches("Test123!!", "encodedPassword");
  }
}
