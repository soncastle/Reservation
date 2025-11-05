package com.reservation.movie.reservation.controller;

import com.reservation.movie.reservation.reservationDto.CancelDto;
import com.reservation.movie.reservation.service.ReservationService;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationRestController {
  private final ReservationService reservationService;

  @PostMapping("/movie")
  String reservationMovie(@RequestBody ReservationDto reservationDto){
    return reservationService.reservationMovie(reservationDto);
  }
  @GetMapping("/seats/{movieId}")
  public List<Integer> findAllSeatNumbers(@PathVariable int movieId){
    return reservationService.findAllSeatNumbers(movieId);
  }

  @PostMapping("/seats/cancel")
  String reservationCancel(@RequestBody Map<String, String>data){
    String email = (String) data.get("email");
    String movieTitle = (String) data.get("movieTitle");

    reservationService.reservationCancel(email, movieTitle);

    return "";
  }
}
