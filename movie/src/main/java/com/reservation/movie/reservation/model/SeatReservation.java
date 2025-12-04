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

  @Column(name = "seat_number", nullable = false, length = 1)
  private int seatNumber;

  @Column(name = "movieId", nullable = false, length = 10)
  private int movieId;

  @Column(name = "reservation_State", nullable = false, length = 4)
  private String reservationState;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;
}
