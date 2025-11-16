package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat_reservation",
uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "seat_number"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private int seatNumber;
  private int movieId;
  private String reservationState;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;
}
