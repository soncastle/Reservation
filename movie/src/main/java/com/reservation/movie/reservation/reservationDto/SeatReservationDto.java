package com.reservation.movie.reservation.reservationDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatReservationDto {
  private List<Integer> setNumber;
  private int movieId;
  private String email;
  private String reservationState;
}
