package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

List<SeatReservation> findAllSeatNumberByMovieIdAndReservationState(int movieId, String reservationState);

}
