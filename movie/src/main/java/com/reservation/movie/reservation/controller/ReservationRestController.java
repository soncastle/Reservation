package com.reservation.movie.reservation.controller;

import com.reservation.movie.reservation.service.ReservationService;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationRestController {
  private final ReservationService reservationService;

  @PostMapping("/movie")
  String reservationMovie(@RequestBody ReservationDto reservationDto){
    System.out.println(reservationDto);
    return reservationService.reservationMovie(reservationDto);
  }
  @GetMapping("/seats/{movieId}")
  public List<Integer> findAllSeatNumbers(@PathVariable int movieId){
    System.out.println(reservationService.findAllSeatNumbers(movieId));
    return reservationService.findAllSeatNumbers(movieId);
  }

}
