package com.reservation.movie.reservation.service;
import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class ReservationService {
  private final ReservationRepository reservationRepository;

  public String reservationMovie(ReservationDto reservationDto){
    boolean exists = reservationRepository.existsBySeatNumbersInAndMovieId(reservationDto.getSeatNumbers(), reservationDto.getMovieId());
    if(exists){
      return "이미 예약된 좌석이 포함되어 있습니다!";
    }else{
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = auth.getName();
      LocalDateTime ReservationTime = LocalDateTime.now();
      List<Reservation> reservationList = reservationDto.getSeatNumbers().stream()
          .map(num -> Reservation.builder()
              .seatNumbers(num)
              .movieId(reservationDto.getMovieId())
              .movieTitle(reservationDto.getMovieTitle())
              .email(email)
              .reservationTime(ReservationTime)
              .build())
          .toList();
      reservationRepository.saveAll(reservationList);
      return "예약되었습니다.";
    }
    }

  public List<Integer> findAllSeatNumbers(int movieId) {
    return reservationRepository.findSeatNumbersByMovieId(movieId);
  }

}
