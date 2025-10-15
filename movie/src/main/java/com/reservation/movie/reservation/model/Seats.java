package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="seat_numbers")
    private int seatNumbers;

    private String movieTitle;

    @Column(name="movie_id")
    private int movieId;
//
//    @ManyToOne
//    @JoinColumn(name = "movie_id")
//    private Movie movie;
//
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
