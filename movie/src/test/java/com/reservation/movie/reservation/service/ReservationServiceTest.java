package com.reservation.movie.reservation.service;

import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.reservation.repository.SeatReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

  @InjectMocks
  private ReservationService reservationService;

  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private SeatReservationRepository seatReservationRepository;

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
    when(authentication.getName()).thenReturn("test@test.com");
  }

  @Test
  void reservationSuccess() {
    // given
    ReservationDto dto = new ReservationDto();
    dto.setMovieId(1);
    dto.setMovieTitle("테스트 영화");
    dto.setSeatNumbers(List.of(3, 1, 8));

    // 로그인 세션 통과
    UserDto userDto = new UserDto(); // 기본 생성 가능할 때만
    when(authService.checkUserSession()).thenReturn(userDto);

    // 기존 예약 좌석 없음
    when(seatReservationRepository
        .findAllSeatNumberByMovieIdAndReservationState(1, "예약"))
        .thenReturn(List.of());

    // when
    String result = reservationService.reservationMovie(dto);

    // then
    verify(reservationRepository, times(1))
        .save(any(Reservation.class));

    assertThat(result).isEqualTo("예약되었습니다.");
  }
}