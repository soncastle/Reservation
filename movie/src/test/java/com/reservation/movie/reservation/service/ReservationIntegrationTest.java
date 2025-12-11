package com.reservation.movie.reservation.service;

import com.reservation.movie.reservation.execption.ReservationException;
import com.reservation.movie.reservation.model.SeatReservation;
import com.reservation.movie.reservation.repository.SeatReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.model.CustomUserDetails;
import com.reservation.movie.user.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ReservationIntegrationTest {
  @Autowired
  private ReservationService reservationService;

  @Autowired
  private SeatReservationRepository seatReservationRepository;

  @Test
  void reservationDuplicate(){
    User savedUser = new User();

    savedUser.setEmail("test@naver.com");
    savedUser.setPassword("Test123!!");
    savedUser.setRole("USER");

    CustomUserDetails customUserDetails = new CustomUserDetails(savedUser);
    Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);


    SeatReservation seat = SeatReservation.builder()
        .movieId(1)
        .reservationState("예약")
        .seatNumber(1)
        .build();
    seatReservationRepository.save(seat);
    SeatReservation seats = SeatReservation.builder()
        .movieId(1)
        .reservationState("예약")
        .seatNumber(3)
        .build();

    seatReservationRepository.save(seats);

    ReservationDto dto = new ReservationDto();
    dto.setSeatNumbers(List.of(1, 2, 3, 4, 5));
    dto.setReservationState("예약");
    dto.setEmail("test@naver.com");
    dto.setMovieId(1);
    dto.setMovieTitle("테스트영화");


    assertThatThrownBy(() -> reservationService.reservationMovie(dto))
        .isInstanceOf(ReservationException.class)
        .hasMessage("현재 좌석 중 이미 예약된 좌석이 있습니다.");
  }

}
