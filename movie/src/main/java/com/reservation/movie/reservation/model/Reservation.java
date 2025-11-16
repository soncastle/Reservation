package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    private String movieTitle;
    private int movieId;
    private String email;
    private String reservationTime;
    private String cancelTime;
    private String reservationState;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatReservation> seats = new ArrayList<>();
}