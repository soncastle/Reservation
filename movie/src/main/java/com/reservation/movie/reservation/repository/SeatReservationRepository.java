package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

@Query("SELECT s FROM SeatReservation s WHERE s.movieId = :movieId AND s.reservationState = :reservationState")
List<SeatReservation> findAllSeatNumberByMovieIdAndReservationState(
    @Param("movieId") int movieId, @Param("reservationState") String reservationState);

  @Query("SELECT s.seatNumber FROM SeatReservation s WHERE s.movieId = :movieId AND s.reservationState = :reservationState")
  List<Integer> findSeatNumberByMovieIdAndReservationState(
      @Param("movieId") int movieId, @Param("reservationState") String reservationState);
}
