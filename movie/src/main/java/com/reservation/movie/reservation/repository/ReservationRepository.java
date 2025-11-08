package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.Reservation;
import com.reservation.movie.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  boolean existsBySeatNumbersInAndMovieId(List<Integer> seatNumbers, int movieId);

  @Query("SELECT s.seatNumbers FROM Reservation s WHERE s.movieId = :movieId AND s.reservationState = '예약'")
  List<Integer> findSeatNumbersByMovieId(@Param("movieId") int movieId);

  List<Reservation> findAllByEmailOrderByReservationTimeDesc(String email);

 Optional<Reservation> findByEmailAndReservationTime(String email, String reservationTime);

//  @Modifying
//  @Transactional
//  @Query("UPDATE Reservation r SET r.reservationState = :reservationState WHERE r.email = :email AND r.reservationTime = :reservationTime")
//  int updateReservationState(@Param("reservationState") String reservationState, @Param("email") String email, @Param("reservationTime") String reservationTime);
}

