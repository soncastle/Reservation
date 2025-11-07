package com.reservation.movie.user.userDto;

import com.reservation.movie.reservation.model.Reservation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReservationInfoDto {
    private String email;
    private String movieTitle;
    private int movieId;
    private String seatNumbers;
    private String reservationTime;
    private String cancelTime;
    private String reservationState;


    public UserReservationInfoDto toEntity(){
        return UserReservationInfoDto.builder()
                .email(this.email)
                .movieTitle(this.movieTitle)
                .seatNumbers(this.seatNumbers)
            .reservationState(this.reservationState)
                .build();
    }
    public static UserReservationInfoDto fromEntity(Reservation reservation) {
        return UserReservationInfoDto.builder()
                .email(reservation.getEmail())
                .movieTitle(reservation.getMovieTitle())
                .movieId(reservation.getMovieId())
                .reservationState(reservation.getReservationState())
                .cancelTime(reservation.getCancelTime())
                .reservationTime(reservation.getReservationTime())
                .seatNumbers(
                        reservation.getSeatNumbers().stream()
                                .sorted()
                                .map(String::valueOf)
                                .collect(Collectors.joining(", "))
                )
                .build();
    }
}
