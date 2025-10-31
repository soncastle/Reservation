package com.reservation.movie.user.userDto;

import com.reservation.movie.reservation.model.Reservation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReservationInfoDto {
    private String email;
    private String movieTitle;
    private int movieId;
    private List<Integer> seatNumbers;
    private LocalDateTime reservationTime;


    public UserReservationInfoDto toEntity(){
        return UserReservationInfoDto.builder()
                .email(this.email)
                .movieTitle(this.movieTitle)
                .seatNumbers(this.seatNumbers)
                .build();
    }
    public static UserReservationInfoDto fromEntity(Reservation reservation) {
        return UserReservationInfoDto.builder()
                .email(reservation.getEmail())
                .movieTitle(reservation.getMovieTitle())
                .movieId(reservation.getMovieId())
                .seatNumbers(List.of(reservation.getSeatNumbers()))
                .reservationTime(reservation.getReservationTime())
                .build();
    }

}
