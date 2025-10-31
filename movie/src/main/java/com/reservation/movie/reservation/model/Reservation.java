package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="seat_numbers")
    private int seatNumbers;

    private String movieTitle;

    @Column(name="movie_id")
    private int movieId;

    private String email;

    private LocalDateTime reservationTime;

}