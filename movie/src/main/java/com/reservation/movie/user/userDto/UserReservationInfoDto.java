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
                .seatNumbers(
                        reservation.getSeatNumbers().stream()
                                .sorted()
                                .map(String::valueOf)
                                .collect(Collectors.joining(", ")) // ✅ 대괄호 없이 "2, 3, 4" 형태
                )
                .reservationTime(reservation.getReservationTime())
                .build();
    }

}
