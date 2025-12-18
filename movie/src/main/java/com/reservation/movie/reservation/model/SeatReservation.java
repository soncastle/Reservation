package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat_reservation",
uniqueConstraints = @UniqueConstraint(
    name = "uk_seat_movie",
    columnNames = {"movie_id", "seat_number"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @Column(name = "seat_number", nullable = false)
  private int seatNumber;

  @Column(name = "movie_Id", nullable = false)
  private int movieId;

  @Column(name = "reservation_State", nullable = false)
  private String reservationState;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;
}
