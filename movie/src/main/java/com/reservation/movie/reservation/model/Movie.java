package com.reservation.movie.reservation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String movieTitle;

//  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//  private List<Seats> seats;
}