package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @ElementCollection
    private List<Integer> seatNumbers;

    private String movieTitle;

    @Column(name="movie_id")
    private int movieId;

    private String email;

    private String reservationTime;

    private String cancelTime;

    private String reservationState;

}