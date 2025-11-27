package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

@Query("SELECT r FROM Reservation r WHERE r.email = :email ORDER BY r.reservationTime DESC")
List<Reservation> findAllByEmailOrderByReservationTimeDesc(@Param("email") String email);

@Query("SELECT r FROM Reservation r WHERE r.email = :email AND r.reservationTime = :reservationTime")
 Optional<Reservation> findByEmailAndReservationTime(@Param("email") String email, @Param("reservationTime") String reservationTime);


//  @Modifying
//  @Transactional
//  @Query("UPDATE Reservation r SET r.reservationState = :reservationState WHERE r.email = :email AND r.reservationTime = :reservationTime")
//  int updateReservationState(@Param("reservationState") String reservationState, @Param("email") String email, @Param("reservationTime") String reservationTime);
}

