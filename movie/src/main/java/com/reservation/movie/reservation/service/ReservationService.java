package com.reservation.movie.reservation.service;
import com.reservation.movie.execption.ErrorCode;
import com.reservation.movie.reservation.execption.ReservationException;
import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.reservation.model.SeatReservation;
import com.reservation.movie.reservation.repository.ReservationRepository;
import com.reservation.movie.reservation.repository.SeatReservationRepository;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.repository.UserRepository;
import com.reservation.movie.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final SeatReservationRepository seatReservationRepository;
  private final AuthService authService;

  public String reservationMovie(ReservationDto reservationDto){
    authService.checkUserSession();
    if(reservationDto.getSeatNumbers() == null || reservationDto.getSeatNumbers().isEmpty()) throw new ReservationException(ErrorCode.SEAT_NOT_CHOOSE);
    List<SeatReservation> existsSeats = seatReservationRepository.findAllSeatNumberByMovieIdAndReservationState(reservationDto.getMovieId(), "예약");

    List<Integer> requestSeats = reservationDto.getSeatNumbers();

    Set<Integer> existsSeatNumbers = existsSeats.stream()
        .map(SeatReservation::getSeatNumber)
        .collect(Collectors.toSet());

    boolean hasDuplicate = existsSeatNumbers.stream().anyMatch(requestSeats::contains);

    if(hasDuplicate){
      throw new ReservationException(ErrorCode.SEAT_ALREADY_RESERVED, "현재 좌석 중 이미 예약된 좌석이 있습니다.");
    }

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = auth.getName();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String formattedTime = LocalDateTime.now().format(formatter);

      Reservation reservation = Reservation.builder()
              .movieId(reservationDto.getMovieId())
              .movieTitle(reservationDto.getMovieTitle())
              .email(email)
              .reservationTime(formattedTime)
              .reservationState("예약")
              .build();

      List<Integer> sortSeatNumbers = reservationDto.getSeatNumbers().stream()
          .sorted()
          .toList();

      List<SeatReservation> seatEntities = sortSeatNumbers.stream()
              .map(seatNumber -> SeatReservation.builder()
                  .movieId(reservationDto.getMovieId())
                  .seatNumber(seatNumber)
                  .reservationState("예약")
                  .reservation(reservation)
                  .build())
                  .toList();

      reservation.setSeats(seatEntities);
      reservationRepository.save(reservation);
      return "예약되었습니다.";

    }

  public String reservationCancel(String email, String reservationTime){
    Reservation reservation = reservationRepository
        .findByEmailAndReservationTime(email, reservationTime)
        .orElseThrow(() -> new ReservationException(ErrorCode.RESERVATION_NOT_FOUND, "예약 내역이 없습니다."));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedTime = LocalDateTime.now().format(formatter);
    reservation.setCancelTime(formattedTime);
    reservation.setReservationState("취소");

    reservation.getSeats().forEach(seat -> {
      seat.setReservationState("취소");
    });

    reservationRepository.save(reservation);
    return "ok";
  }

  public List<Integer> findAllSeatNumbers(int movieId) {
    List<Integer> seatList = seatReservationRepository.findSeatNumberByMovieIdAndReservationState(movieId, "예약");
    if(seatList == null || seatList.isEmpty()){
      throw new ReservationException(ErrorCode.RESERVATION_NOT_FOUND);
    }
    return seatList;
  }

}
