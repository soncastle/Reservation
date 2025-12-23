package com.reservation.movie.reservation.reservationDto;

import com.reservation.movie.reservation.model.Reservation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
  private int movieId;
  private String movieTitle;
  private List<Integer> seatNumbers;
  private String email;
  private String reservationState;
  private String cancelTime;
  private String refunded;
}
