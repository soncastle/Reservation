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


  List<Reservation> findAllByEmailOrderByReservationTimeDesc(String email);

 Optional<Reservation> findByEmailAndReservationTime(String email, String reservationTime);

 List<Integer> findSeatsInByMovieIdAndReservationState(int movieId, String ReservationState);


//  @Modifying
//  @Transactional
//  @Query("UPDATE Reservation r SET r.reservationState = :reservationState WHERE r.email = :email AND r.reservationTime = :reservationTime")
//  int updateReservationState(@Param("reservationState") String reservationState, @Param("email") String email, @Param("reservationTime") String reservationTime);
}

