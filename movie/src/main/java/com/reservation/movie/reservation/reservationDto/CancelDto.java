package com.reservation.movie.reservation.reservationDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelDto {
 private String email;
 private String movieTitle;
 private String reservationState;
 private String refunded;

}
