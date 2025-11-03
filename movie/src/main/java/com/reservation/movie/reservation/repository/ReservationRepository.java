package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  boolean existsBySeatNumbersInAndMovieId(List<Integer> seatNumbers, int movieId);

  @Query("SELECT s.seatNumbers FROM Reservation s WHERE s.movieId = :movieId")
  List<Integer> findSeatNumbersByMovieId(@Param("movieId") int movieId);

  List<Reservation> findAllByEmailOrderByReservationTimeDesc(String email);
}

