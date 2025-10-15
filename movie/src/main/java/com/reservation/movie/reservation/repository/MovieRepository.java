package com.reservation.movie.reservation.repository;

import com.reservation.movie.reservation.model.Movie;
import com.reservation.movie.reservation.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
