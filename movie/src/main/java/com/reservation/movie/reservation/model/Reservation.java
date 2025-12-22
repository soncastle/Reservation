package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "reservation_time", nullable = false)
    private String reservationTime;

    @Column(name = "cancelTime")
    private String cancelTime;

    @Column(name = "reservation_state", nullable = false)
    private String reservationState;

    @Column(name = "refunded")
    private String refunded;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatReservation> seats = new ArrayList<>();
}