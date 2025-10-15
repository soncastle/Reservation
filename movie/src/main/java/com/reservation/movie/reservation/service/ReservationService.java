package com.reservation.movie.reservation.service;
import com.reservation.movie.reservation.model.Seats;
import com.reservation.movie.reservation.repository.SeatsRepository;
import com.reservation.movie.reservationDto.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Builder
public class ReservationService {
  private final SeatsRepository seatsRepository;

  public String reservationMovie(ReservationDto reservationDto){
    boolean exists = seatsRepository.existsBySeatNumbersInAndMovieId(reservationDto.getSeatNumbers(), reservationDto.getMovieId());
    if(exists){
      return "이미 예약된 좌석이 포함되어 있습니다!";
    }else{
      List<Seats> seatsList = reservationDto.getSeatNumbers().stream()
          .map(num -> Seats.builder()
              .seatNumbers(num)
              .movieId(reservationDto.getMovieId())
              .movieTitle(reservationDto.getMovieTitle())
              .build())
          .toList();
      seatsRepository.saveAll(seatsList);
      return "예약되었습니다.";
    }
    }

  public List<Integer> findAllSeatNumbers(int movieId) {
    return seatsRepository.findSeatNumbersByMovieId(movieId);
  }
  }
