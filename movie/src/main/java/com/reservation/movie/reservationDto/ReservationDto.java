package com.reservation.movie.reservationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
  private  int movieId;
  private String movieTitle;
  private List<Integer> seatNumbers;
//  private String email;

}
