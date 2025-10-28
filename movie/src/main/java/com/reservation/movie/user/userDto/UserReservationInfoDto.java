package com.reservation.movie.user.userDto;

import com.reservation.movie.reservation.model.Seats;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
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
    public static UserReservationInfoDto fromEntity(Seats seats) {
        return UserReservationInfoDto.builder()
                .email(seats.getEmail())
                .movieTitle(seats.getMovieTitle())
                .movieId(seats.getMovieId())
                .seatNumbers(List.of(seats.getSeatNumbers()))
                .reservationTime(seats.getReservationTime())
                .build();
    }

}
