package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userEmail; // 예약자 정보

  private String movieTitle;

  private LocalDateTime reservedAt;

  // 한 번의 예약에 여러 좌석 가능
  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
  private List<Seats> seats;
}