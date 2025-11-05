package com.reservation.movie.reservation.service;
import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.model.User;
import com.reservation.movie.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final UserRepository userRepository;

  public String reservationMovie(ReservationDto reservationDto){
    boolean exists = reservationRepository.existsBySeatNumbersInAndMovieId(reservationDto.getSeatNumbers(), reservationDto.getMovieId());
    if(exists){
      return "이미 예약된 좌석이 포함되어 있습니다!";
    }else{
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = auth.getName();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String formattedTime = LocalDateTime.now().format(formatter);

      List<Integer> sortedSeats = reservationDto.getSeatNumbers().stream()
              .sorted()
              .toList();

      Reservation reservation = Reservation.builder()
              .movieId(reservationDto.getMovieId())
              .movieTitle(reservationDto.getMovieTitle())
              .email(email)
              .seatNumbers(sortedSeats)
              .reservationTime(formattedTime)
              .reservationState("예약")
              .build();

      reservationRepository.save(reservation);
      return "예약되었습니다.";
    }
    }

  public void reservationCancel(String email, String reservationTime){
    Reservation reservation  = (Reservation) reservationRepository.findByEmailAndReservationTime(email, reservationTime);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedTime = LocalDateTime.now().format(formatter);
    reservation.setCancelTime(formattedTime);
    reservation.setReservationState("취소");
    reservationRepository.save(reservation);

  }

  public List<Integer> findAllSeatNumbers(int movieId) {
    return reservationRepository.findSeatNumbersByMovieId(movieId);
  }

}
